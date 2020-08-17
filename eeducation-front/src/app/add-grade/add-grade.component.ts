import {Component, OnInit} from '@angular/core';
import {Course} from '../model/Course';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CourseService} from '../service/course.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AddGrade} from '../model/AddGrade';
import {GradeService} from '../service/grade.service';

@Component({
  selector: 'app-add-grade',
  templateUrl: './add-grade.component.html',
  styleUrls: ['./add-grade.component.scss']
})
export class AddGradeComponent implements OnInit {

  course: Course;
  gradeForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private  courseService: CourseService,
              private gradeService: GradeService,
              private router: Router,
              private route: ActivatedRoute,
              private snackBar: MatSnackBar) {
  }

  get student() {
    return this.gradeForm.controls.student.value as number;
  }

  get grade() {
    return this.gradeForm.controls.grade.value as string;
  }

  ngOnInit(): void {
    this.gradeForm = this.formBuilder.group({
      student: ['', [
        Validators.required
      ]],
      grade: ['', [
        Validators.required,
      ]]
    });
    const courseId = Number(this.route.snapshot.paramMap.get('courseId'));
    this.courseService.getCourse(courseId).subscribe(
      response => {
        this.course = response;
        console.log(response);
      },
      error => {
        alert(error);
      }
    );
  }

  addGrade() {
    const addGrade = new AddGrade(this.student, this.course.id, this.grade);
    this.gradeService.teacherAddsGrade(addGrade).subscribe(
      response => {
        this.router.navigateByUrl('course/' + this.course.id);
      }
    );
  }

}
