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


const routes: Routes = [
  {path: '', component: IndexComponent, canActivate: [NotLoggedInGuard]},
  {path: 'login', component: LoginComponent, canActivate: [NotLoggedInGuard]},
  {path: 'register', component: RegisterComponent, canActivate: [NotLoggedInGuard]},
  {path: 'home', component: HomeComponent, canActivate: [LoggedInGuard]},
  {path: 'create-course', component: CreateCourseComponent, canActivate: [LoggedInGuard]},
  {path: 'edit-course/:id', component: EditCourseComponent, canActivate: [LoggedInGuard]},
  {path: 'course/:id', component: CourseDetailsComponent, canActivate: [LoggedInGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
