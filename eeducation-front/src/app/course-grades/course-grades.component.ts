import {Component, Input, OnInit} from '@angular/core';
import {Course} from '../model/Course';
import {Router} from '@angular/router';

@Component({
  selector: 'app-course-grades',
  templateUrl: './course-grades.component.html',
  styleUrls: ['./course-grades.component.scss']
})
export class CourseGradesComponent implements OnInit {

  @Input() course: Course;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  addGrade() {
    this.router.navigateByUrl('add-grade/' + this.course.id);
  }

}
