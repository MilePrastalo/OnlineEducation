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
  displayedColumns = ['studentName', 'lessonName'];

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }


}
