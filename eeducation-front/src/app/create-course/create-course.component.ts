import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {CourseService} from '../service/course.service';
import {CreateCourse} from '../model/CreateCourse';

@Component({
  selector: 'app-create-course',
  templateUrl: './create-course.component.html',
  styleUrls: ['./create-course.component.scss']
})
export class CreateCourseComponent implements OnInit {

  courseForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private courseService: CourseService,
              private snackBar: MatSnackBar, private router: Router) {
  }

  get name() {
    return this.courseForm.controls.name.value as string;
  }

  get begins() {
    return this.courseForm.controls.begins.value as string;
  }

  get ends() {
    return this.courseForm.controls.ends.value as string;
  }

  get school() {
    return this.courseForm.controls.school.value as number;
  }

  get teachers() {
    return this.courseForm.controls.teachers.value as string;
  }


  ngOnInit(): void {
    this.courseForm = this.formBuilder.group({
      name: ['', [
        Validators.required
      ]],
      begins: ['', [
        Validators.required,
      ]],
      ends: ['', [
        Validators.required
      ]],
      school: ['', [
        Validators.required
      ]],
      teachers: ['', [
        Validators.required
      ]]
    });
  }

  onCourseCreate() {
    const t = this.teachers.split(',');
    const createCourse = new CreateCourse(this.name, this.begins, this.ends, this.school, t);
    this.courseService.createCourse(createCourse).subscribe(
      response => {

      },
      error => {
      }
    );
  }

}
