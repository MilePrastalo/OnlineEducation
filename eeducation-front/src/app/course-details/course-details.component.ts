import {Component, OnInit} from '@angular/core';
import {Course} from '../model/Course';
import {CourseService} from '../service/course.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.scss']
})
export class CourseDetailsComponent implements OnInit {

  course: Course;
  courseId: number;

  constructor(private courseService: CourseService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.courseId = Number(this.route.snapshot.paramMap.get('id'));
    this.courseService.getCourse(this.courseId).subscribe(
      response => {
        this.course = response;
      },
      error => {
        alert(error);
      }
    );

  }

}
