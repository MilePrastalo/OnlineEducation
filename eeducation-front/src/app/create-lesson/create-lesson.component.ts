import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CourseService} from '../service/course.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ActivatedRoute, Router} from '@angular/router';
import {CreateLesson} from '../model/CreateLesson';
import {LessonService} from '../service/lesson.service';
import {LessonLink} from '../model/LessonLink';
import {TeacherService} from '../service/teacher.service';

@Component({
  selector: 'app-create-lesson',
  templateUrl: './create-lesson.component.html',
  styleUrls: ['./create-lesson.component.scss']
})
export class CreateLessonComponent implements OnInit {
  image: string;
  lessonForm: FormGroup;
  courseId: number;
  htmlContentFromQuill = '';
  lessonLinks: Array<LessonLink>;

  constructor(private lessonService: LessonService,
              public dialog: MatDialog,
              private formBuilder: FormBuilder,
              private courseService: CourseService,
              private snackBar: MatSnackBar,
              private router: Router,
              private route: ActivatedRoute,
              private teacherService: TeacherService) {
  }

  get name() {
    return this.lessonForm.controls.name.value as string;
  }

  get date() {
    return this.lessonForm.controls.date.value as string;
  }

  get lessonType() {
    return this.lessonForm.controls.lessonType.value as string;
  }

  get link() {
    return this.lessonForm.controls.link.value as string;
  }

  ngOnInit(): void {
    this.courseId = Number(this.route.snapshot.paramMap.get('courseId'));
    this.lessonForm = this.formBuilder.group({
      name: ['', [
        Validators.required
      ]],
      link: ['', []],
      date: ['', [
        Validators.required,
      ]], lessonType: ['', [
        Validators.required]]
    })
    ;
    this.lessonForm.controls.lessonType.setValue('NEW');

    this.teacherService.getTeacherLessons().subscribe(
      response => {
        this.lessonLinks = response;
      }
    );
  }


  onLessonSubmit() {
    let lessonPath: string = null;
    let createLesson: CreateLesson = null;
    const quill = (document.getElementById('quill') as HTMLElement);
    if (this.lessonType === 'LINK') {
      lessonPath = this.link;
      createLesson = new CreateLesson(this.courseId, '', this.name, this.date, lessonPath);
    } else {
      createLesson = new CreateLesson(this.courseId, this.htmlContentFromQuill, this.name, this.date, lessonPath);
    }
    this.lessonService.createLesson(createLesson).subscribe(
      response => {
        console.log(response);
        this.router.navigateByUrl('course/' + this.courseId);
      }
    );
  }

  quillEditorChange($event) {
    this.htmlContentFromQuill = $event.html;
  }

  displayToolBox(): boolean {
    const type = this.lessonType;
    if (type === 'NEW') {
      return true;
    }
    return false;
  }
}
