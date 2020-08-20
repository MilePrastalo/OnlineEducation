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

  textArea: HTMLTextAreaElement;
  preview: HTMLElement;
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

  ngOnInit(): void {
    this.lessonId = Number(this.route.snapshot.paramMap.get('id'));
    this.textArea = document.getElementById('textArea') as HTMLTextAreaElement;
    this.preview = document.getElementById('preview') as HTMLElement;
    this.lessonForm = this.formBuilder.group({
      name: ['', [
        Validators.required
      ]],
      date: ['', [
        Validators.required,
      ]]
    });
    this.lessonService.getLesson(this.lessonId).subscribe(
      response => {
        this.lesson = response;
        this.textArea.value = response.lessonContent;
        this.lessonForm.controls.name.setValue(response.name);
        this.lessonForm.controls.date.setValue(response.date);
        this.courseId = this.lesson.courseId;
        this.changePreview();
      }, error => {

      });


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
    const editLesson = new EditLesson(this.lessonId, this.courseId, this.textArea.value, this.name, this.date);
    this.lessonService.editLesson(editLesson).subscribe(
      response => {
        this.router.navigateByUrl('lesson/' + this.lessonId);
      }
    );
  }

}
