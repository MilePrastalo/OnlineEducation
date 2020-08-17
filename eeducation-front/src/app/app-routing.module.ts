import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {IndexComponent} from './index/index.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {HomeComponent} from './home/home.component';
import {LoggedInGuard} from './guards/logged-in.guard';
import {NotLoggedInGuard} from './guards/not-logged-in.guard';
import {CreateCourseComponent} from './create-course/create-course.component';
import {EditCourseComponent} from './edit-course/edit-course.component';
import {CourseDetailsComponent} from './course-details/course-details.component';
import {SchoolDetailsComponent} from './school-details/school-details.component';
import {CreateTestComponent} from './create-test/create-test.component';
import {TestComponent} from './test/test.component';
import {CreateAbsenceComponent} from './create-absence/create-absence.component';
import {CreateLessonComponent} from './create-lesson/create-lesson.component';
import {LessonComponent} from './lesson/lesson.component';
import {AddGradeComponent} from './add-grade/add-grade.component';
import {StudentHomeComponent} from './student-home/student-home.component';


const routes: Routes = [
  {path: '', component: IndexComponent, canActivate: [NotLoggedInGuard]},
  {path: 'login', component: LoginComponent, canActivate: [NotLoggedInGuard]},
  {path: 'register', component: RegisterComponent, canActivate: [NotLoggedInGuard]},
  {path: 'home', component: HomeComponent, canActivate: [LoggedInGuard]},
  {path: 'create-course', component: CreateCourseComponent, canActivate: [LoggedInGuard]},
  {path: 'edit-course/:id', component: EditCourseComponent, canActivate: [LoggedInGuard]},
  {path: 'course/:id', component: CourseDetailsComponent, canActivate: [LoggedInGuard]},
  {path: 'school/:id', component: SchoolDetailsComponent, canActivate: [LoggedInGuard]},
  {path: 'create-test/:course', component: CreateTestComponent, canActivate: [LoggedInGuard]},
  {path: 'test/:id', component: TestComponent, canActivate: [LoggedInGuard]},
  {path: 'create-absence/:courseId', component: CreateAbsenceComponent, canActivate: [LoggedInGuard]},
  {path: 'create-lesson/:courseId', component: CreateLessonComponent, canActivate: [LoggedInGuard]},
  {path: 'lesson/:id', component: LessonComponent, canActivate: [LoggedInGuard]},
  {path: 'add-grade/:courseId', component: AddGradeComponent, canActivate: [LoggedInGuard]},
  {path: 'student-home', component: StudentHomeComponent, canActivate: [LoggedInGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
