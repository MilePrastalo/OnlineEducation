import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ImageDialogComponent} from '../image-dialog/image-dialog.component';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CourseService} from '../service/course.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ActivatedRoute, Router} from '@angular/router';
import {CreateLesson} from '../model/CreateLesson';
import {LessonService} from '../service/lesson.service';

@Component({
  selector: 'app-create-lesson',
  templateUrl: './create-lesson.component.html',
  styleUrls: ['./create-lesson.component.scss']
})
export class CreateLessonComponent implements OnInit {
  textArea: HTMLTextAreaElement;
  preview: HTMLElement;
  image: string;
  lessonForm: FormGroup;
  courseId: number;

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

  get lessonType() {
    return this.lessonForm.controls.lessonType.value as string;
  }

  get link() {
    return this.lessonForm.controls.link.value as string;
  }

  ngOnInit(): void {
    this.courseId = Number(this.route.snapshot.paramMap.get('courseId'));
    this.textArea = document.getElementById('textArea') as HTMLTextAreaElement;
    this.preview = document.getElementById('preview') as HTMLElement;
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
  }


  setElement(elemet) {
    const start = this.textArea.selectionStart;
    const end = this.textArea.selectionEnd;
    let sel = this.textArea.value.substring(start, end);
    sel = '<' + elemet + '>' + sel + '</' + elemet + '>';
    this.textArea.setRangeText(sel, start, end);
    this.changePreview();
  }

  setImage() {
    const dialogRef = this.dialog.open(ImageDialogComponent, {
      width: '250px',
      data: {name: 'image', image: this.image}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const start = this.textArea.selectionStart;
        const sel = '<img width="40%" src="' + result + '">';
        this.textArea.setRangeText(sel, start, start);
        this.changePreview();
      }
    });
  }

  setVideo() {
    const dialogRef = this.dialog.open(ImageDialogComponent, {
      width: '250px',
      data: {name: 'video', image: this.image}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const start = this.textArea.selectionStart;
        const split = result.split('?v=');
        const id = split[split.length - 1];
        console.log(id);
        const sel = ' <iframe width="560" height="315" src="https://www.youtube.com/embed/' + id + '"></iframe>';
        this.textArea.setRangeText(sel, start, start);
        this.changePreview();
      }
    });
  }

  changePreview() {
    console.log('change preview');
    if (!this.textArea) {
      this.textArea = document.getElementById('textArea') as HTMLTextAreaElement;
    }
    if (!this.preview) {
      this.preview = document.getElementById('preview') as HTMLElement;
    }
    const tex = this.textArea.value;
    const splitted = tex.split('\n');
    this.preview.innerHTML = '';
    for (const part of splitted) {
      this.preview.innerHTML = this.preview.innerHTML + '<br>' + part;
    }
  }

  textChange(event) {
    this.changePreview();
  }

  onLessonSubmit() {
    let lessonPath: string = null;
    let createLesson: CreateLesson = null;
    if (this.lessonType === 'LINK') {
      lessonPath = this.link;
      createLesson = new CreateLesson(this.courseId, '', this.name, this.date, lessonPath);
    } else {
      createLesson = new CreateLesson(this.courseId, this.textArea.value, this.name, this.date, lessonPath);
    }
    this.lessonService.createLesson(createLesson).subscribe(
      response => {
        console.log(response);
        this.router.navigateByUrl('course/' + this.courseId);
      }
    );
  }

  displayToolBox(): boolean {
    const type = this.lessonType;
    if (type === 'NEW') {
      return true;
    }
    return false;
  }
}
