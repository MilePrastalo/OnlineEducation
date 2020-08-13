import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CourseService} from '../service/course.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ActivatedRoute, Router} from '@angular/router';
import {TeacherService} from '../service/teacher.service';
import {Course} from '../model/Course';
import {EditCourse} from '../model/EditCourse';

@Component({
  selector: 'app-edit-course',
  templateUrl: './edit-course.component.html',
  styleUrls: ['./edit-course.component.scss']
})
export class EditCourseComponent implements OnInit {
  courseForm: FormGroup;
  courseId: number;
  course: Course;

  constructor(private formBuilder: FormBuilder, private courseService: CourseService, private route: ActivatedRoute,
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

  get description() {
    return this.courseForm.controls.description.value as string;
  }

  ngOnInit(): void {
    this.courseId = Number(this.route.snapshot.paramMap.get('id'));
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
      description: ['', [
        Validators.required
      ]]
    });

    this.courseService.getCourse(this.courseId).subscribe(
      response => {
        this.course = response;
        this.courseForm.controls.ends.setValue(this.course.ends);
        this.courseForm.controls.name.setValue(this.course.name);
        this.courseForm.controls.begins.setValue(this.course.begins);
        this.courseForm.controls.description.setValue(this.course.description);

      },
      error => {
        console.log(error);
        this.snackBar.open('Error getting course');
      }
    );

  }

  edit() {
    const editCourse = new EditCourse(this.course.id, this.name, this.begins, this.ends, this.description);
    this.courseService.editCourse(editCourse).subscribe(
      response => {
        this.snackBar.open('Course has been edited');
        this.router.navigateByUrl('/course/' + this.courseId);
      },
      error => {
        this.snackBar.open('Error editing course');

      }
    );
  }

}
