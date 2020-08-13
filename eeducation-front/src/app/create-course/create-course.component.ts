import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {CourseService} from '../service/course.service';
import {CreateCourse} from '../model/CreateCourse';
import {School} from '../model/School';
import {TeacherService} from '../service/teacher.service';

@Component({
  selector: 'app-create-course',
  templateUrl: './create-course.component.html',
  styleUrls: ['./create-course.component.scss']
})
export class CreateCourseComponent implements OnInit {

  courseForm: FormGroup;
  schools: Array<School>;

  constructor(private formBuilder: FormBuilder, private courseService: CourseService,
              private snackBar: MatSnackBar, private router: Router, private teacherService: TeacherService) {
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

  get description() {
    return this.courseForm.controls.description.value as string;
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
      ]],
      description: ['', [
        Validators.required
      ]]
    });

    this.teacherService.getTeacherSchools().subscribe(
      response => {
        console.log('succ', response);
        this.schools = response;
      },
      error => {
        console.log(error);
        alert(error);
      }
    );
  }

  onCourseCreate() {
    console.log(this.begins);
    const t = this.teachers.split(',');
    const createCourse = new CreateCourse(this.name, this.begins, this.ends, this.school, t, true, this.description);
    console.log(createCourse);
    this.courseService.addCourse(createCourse).subscribe(
      response => {
        console.log(response);
        this.snackBar.open('Course has been created');
        this.router.navigateByUrl('');
      },
      error => {
        this.snackBar.open('Error creating course');
      }
    );
  }

}
