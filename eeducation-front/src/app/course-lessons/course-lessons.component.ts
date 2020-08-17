import {Component, Input, OnInit} from '@angular/core';
import {Course} from '../model/Course';
import {Router} from '@angular/router';

@Component({
  selector: 'app-course-lessons',
  templateUrl: './course-lessons.component.html',
  styleUrls: ['./course-lessons.component.scss']
})
export class CourseLessonsComponent implements OnInit {

  @Input() course: Course;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  createLesson() {
    this.router.navigateByUrl('create-lesson/' + this.course.id);
  }

  viewLesson(id: number) {
    this.router.navigateByUrl('lesson/' + id);

  }

}
