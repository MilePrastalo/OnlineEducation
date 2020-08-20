package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.dto.CreateLessonDto
import rs.eeducation.dto.EditLessonDto
import rs.eeducation.model.Lesson
import rs.eeducation.model.LessonContent
import rs.eeducation.repository.LessonContentRepository
import rs.eeducation.repository.LessonRepository
import javax.persistence.EntityNotFoundException


@Service
class LessonService(private val lessonRepository: LessonRepository,
                    private val courseService: CourseService,
                    private val userService: UserService,
                    private val lessonContentRepository: LessonContentRepository) {

    fun findById(lessonId: Long): Lesson {
        return lessonRepository.findById(lessonId).orElseThrow { EntityNotFoundException("Lesson does not exist") }
    }

    fun save(lesson: Lesson): Lesson {
        return lessonRepository.save(lesson)
    }

    fun createLesson(createLessonDto: CreateLessonDto): Lesson {
        val course = courseService.findById(createLessonDto.courseId)

        userService.teacherTeacherCourse(userService.getLoggedInUser()!!, course)
        val lesson = save(Lesson(null, course, HashSet(), HashSet(), createLessonDto.name, createLessonDto.date))
        val lessonContent = LessonContent(null, lesson.id!!, createLessonDto.lessonContent)
        lessonContentRepository.save(lessonContent);
        (course.lessons as MutableSet).add(lesson)
        courseService.save(course)

        return lesson
    }

    fun editLesson(editLessonDto: EditLessonDto): Lesson {
        val course = courseService.findById(editLessonDto.courseId)
        val lesson = findById(editLessonDto.id)
        userService.teacherTeacherCourse(userService.getLoggedInUser()!!, course)
        val lessonContent = lessonContentRepository.findByLessonId(lesson.id!!)
        lessonContent.lessonContent = editLessonDto.lessonContent
        lessonContentRepository.save(lessonContent)
        lesson.name = editLessonDto.name
        lesson.date = editLessonDto.date
        return save(lesson)
    }

    fun deleteLesson(lessonId: Long) {
        val lesson = findById(lessonId)
        val course = lesson.course
        (course.lessons as MutableSet).remove(course.lessons.find { l->l.id == lesson.id })
        courseService.save(course)
        userService.teacherTeacherCourse(userService.getLoggedInUser()!!, course)
        lessonRepository.deleteById(lessonId)
    }

    fun getLessonContent(lesson: Lesson): String {
        return lessonContentRepository.findByLessonId(lesson.id!!).lessonContent
    }

}
