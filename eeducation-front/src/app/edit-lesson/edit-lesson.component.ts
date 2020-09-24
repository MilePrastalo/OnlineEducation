import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LessonService} from '../service/lesson.service';
import {MatDialog} from '@angular/material/dialog';
import {CourseService} from '../service/course.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ActivatedRoute, Router} from '@angular/router';
import {ImageDialogComponent} from '../image-dialog/image-dialog.component';
import {Lesson} from '../model/Lesson';
import {EditLesson} from '../model/EditLesson';

@Component({
  selector: 'app-edit-lesson',
  templateUrl: './edit-lesson.component.html',
  styleUrls: ['./edit-lesson.component.scss']
})
export class EditLessonComponent implements OnInit {
  image: string;
  lessonForm: FormGroup;
  courseId: number;
  lessonId: number;
  lesson: Lesson;
  constructor(private lessonService: LessonService,
              public dialog: MatDialog,
              private formBuilder: FormBuilder,
              private courseService: CourseService,
              private snackBar: MatSnackBar,
              private router: Router,
              private route: ActivatedRoute) {
  }

  get name() {
    return this.lessonForm.controls.name.value as string;
  }

  get date() {
    return this.lessonForm.controls.date.value as string;
  }
  get content() {
    return this.lessonForm.controls.content.value as string;
  }

  ngOnInit(): void {
    this.lessonId = Number(this.route.snapshot.paramMap.get('id'));
    this.lessonForm = this.formBuilder.group({
      name: ['', [
        Validators.required
      ]],
      date: ['', [
        Validators.required,
      ]],
      content: ['', [
        Validators.required,
      ]],
    });
    this.lessonService.getLesson(this.lessonId).subscribe(
      response => {
        this.lesson = response;
        this.lessonForm.controls.content.setValue(this.lesson.lessonContent);
        this.lessonForm.controls.name.setValue(response.name);
        this.lessonForm.controls.date.setValue(response.date);
        this.courseId = this.lesson.courseId;
      }, error => {

      });
  }


  onLessonSubmit() {
    const editLesson = new EditLesson(this.lessonId, this.courseId, this.content, this.name, this.date);
    this.lessonService.editLesson(editLesson).subscribe(
      response => {
        this.router.navigateByUrl('lesson/' + this.lessonId);
      }
    );
  }

}
