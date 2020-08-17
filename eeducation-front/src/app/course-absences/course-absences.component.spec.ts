import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseAbsencesComponent } from './course-absences.component';

describe('CourseAbsencesComponent', () => {
  let component: CourseAbsencesComponent;
  let fixture: ComponentFixture<CourseAbsencesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseAbsencesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseAbsencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
