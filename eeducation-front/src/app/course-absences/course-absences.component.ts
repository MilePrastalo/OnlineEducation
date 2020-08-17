import {Component, Input, OnInit} from '@angular/core';
import {Course} from '../model/Course';
import {Router} from '@angular/router';

@Component({
  selector: 'app-course-absences',
  templateUrl: './course-absences.component.html',
  styleUrls: ['./course-absences.component.scss']
})
export class CourseAbsencesComponent implements OnInit {

  @Input() course: Course;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  addAbsence() {
    this.router.navigateByUrl('create-absence/' + this.course.id);
  }

}
