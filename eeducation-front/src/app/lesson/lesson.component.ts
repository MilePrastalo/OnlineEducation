import {Component, OnInit} from '@angular/core';
import {LessonService} from '../service/lesson.service';
import {Lesson} from '../model/Lesson';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AddComment} from '../model/AddComment';
import {CommentService} from '../service/comment.service';
import {CourseService} from '../service/course.service';
import {Course} from '../model/Course';
import {VisibilityService} from '../service/visibility.service';

@Component({
  selector: 'app-lesson',
  templateUrl: './lesson.component.html',
  styleUrls: ['./lesson.component.scss']
})
export class LessonComponent implements OnInit {
  lesson: Lesson;
  lessonId: number;
  lessonContent: HTMLElement;
  addCommentForm: FormGroup;
  course: Course;

  constructor(private lessonService: LessonService,
              private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private commentService: CommentService,
              private courseService: CourseService,
              private visibilityService: VisibilityService) {
  }

  get commentText() {
    return this.addCommentForm.controls.commentText.value as string;
  }

  ngOnInit(): void {
    this.addCommentForm = this.formBuilder.group({
      commentText: ['', [
        Validators.required
      ]]
    });
    this.lessonContent = document.getElementById('lessonContent') as HTMLElement;
    this.lessonId = Number(this.route.snapshot.paramMap.get('id'));
    this.lessonService.getLesson(this.lessonId).subscribe(
      response => {
        this.lesson = response;
        this.lessonContent.innerHTML = '';
        const split = this.lesson.lessonContent.split('\n');
        for (const part of split) {
          this.lessonContent.innerHTML = this.lessonContent.innerHTML + '<br/>' + part;
        }
        this.courseService.getCourse(this.lesson.courseId).subscribe(
          courseResponse => {
            this.course = courseResponse;
          }
        );

      }
    );
  }

  delete() {
    this.lessonService.deleteLesson(this.lessonId).subscribe(
      response => {
        this.router.navigateByUrl('course/' + this.lesson.courseId);
      }
    );
  }

  onCommentSubmit() {
    console.log(this.commentText);
    const addComment = new AddComment(this.commentText, this.lesson.id, null);
    this.commentService.addComment(addComment).subscribe(
      response => {
        this.lesson.comments.push(response);
      }
    );
  }

  edit() {
    this.router.navigateByUrl('edit-lesson/' + this.lessonId);
  }

  teacherPartOfCourse(): boolean {
    return this.visibilityService.teacherPartOfCourse(this.course);
  }

}
