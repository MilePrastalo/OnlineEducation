package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.dto.CreateLessonDto
import rs.eeducation.dto.EditLessonDto
import rs.eeducation.model.Lesson
import rs.eeducation.repository.LessonRepository
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.persistence.EntityNotFoundException
import kotlin.collections.HashSet


@Service
class LessonService(private val lessonRepository: LessonRepository,
                    private val courseService: CourseService,
                    private val userService: UserService) {

    fun findById(lessonId: Long): Lesson {
        return lessonRepository.findById(lessonId).orElseThrow { EntityNotFoundException("Lesson does not exist") }
    }

    fun save(lesson: Lesson): Lesson {
        return lessonRepository.save(lesson)
    }

    fun createLesson(createLessonDto: CreateLessonDto): Lesson {
        val course = courseService.findById(createLessonDto.courseId)

        userService.teacherTeacherCourse(userService.getLoggedInUser()!!, course)

        //save to file
        val path = ""
        val name = course.name + "_" + createLessonDto.name + Date().time

        val fileWriter = FileWriter("$path/$name")
        val printWriter = PrintWriter(fileWriter)
        printWriter.print(createLessonDto.lessonContent)
        printWriter.close()

        val lesson = save(Lesson(null, course, HashSet(), HashSet(), path, createLessonDto.name, createLessonDto.date))

        (course.lessons as MutableSet).add(lesson)
        courseService.save(course)

        return lesson
    }

    fun editLesson(editLessonDto: EditLessonDto): Lesson {
        val course = courseService.findById(editLessonDto.courseId)
        val lesson = findById(editLessonDto.id)
        userService.teacherTeacherCourse(userService.getLoggedInUser()!!, course)

        val fileWriter = FileWriter(lesson.pathToFile, false)
        val printWriter = PrintWriter(fileWriter)
        printWriter.print(editLessonDto.lessonContent)
        printWriter.close()

        lesson.name = editLessonDto.name
        lesson.date = editLessonDto.date
        return save(lesson)
    }

    fun deleteLesson(lessonId: Long) {
        val lesson = findById(lessonId)
        val course = lesson.course
        userService.teacherTeacherCourse(userService.getLoggedInUser()!!, course)
        lessonRepository.deleteById(lessonId)
    }

    fun getLessonContent(lesson: Lesson): String {
        return String(Files.readAllBytes(Paths.get(lesson.pathToFile)))
    }

}
