import {Component, OnInit} from '@angular/core';
import {Course} from '../model/Course';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AbsenceService} from '../service/absence.service';
import {CourseService} from '../service/course.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AddAbsence} from '../model/AddAbsence';

@Component({
  selector: 'app-create-absence',
  templateUrl: './create-absence.component.html',
  styleUrls: ['./create-absence.component.scss']
})
export class CreateAbsenceComponent implements OnInit {

  course: Course;
  courseId: number;
  absenceForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private  courseService: CourseService,
              private absenceService: AbsenceService,
              private router: Router,
              private route: ActivatedRoute,
              private snackBar: MatSnackBar) {
  }

  get student() {
    return this.absenceForm.controls.student.value as number;
  }

  get lesson() {
    return this.absenceForm.controls.lesson.value as number;
  }

  ngOnInit(): void {
    this.absenceForm = this.formBuilder.group({
      student: ['', [
        Validators.required
      ]],
      lesson: ['', [
        Validators.required,
      ]]
    });
    this.courseId = Number(this.route.snapshot.paramMap.get('courseId'));
    this.courseService.getCourse(this.courseId).subscribe(
      response => {
        this.course = response;
        console.log(response);
      },
      error => {
        alert(error);
      }
    );
  }

  onAbsenceCreate() {
    console.log(this.student);
    console.log(this.lesson);
    const createAbsence = new AddAbsence(this.student, this.lesson);
    this.absenceService.addAbsence(createAbsence).subscribe(
      next => {
        this.router.navigateByUrl('course/' + this.course.id);
      }
    );
  }

}
