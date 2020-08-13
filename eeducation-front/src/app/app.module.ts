import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { IndexComponent } from './index/index.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AuthenticationService} from './service/authentication.service';
import {PathService} from './service/path.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {TokenInterceptorService} from './service/token-interceptor.service';
import {MaterialModule} from './material/material.module';
import { HomeComponent } from './home/home.component';
import { CreateCourseComponent } from './create-course/create-course.component';
import { EditCourseComponent } from './edit-course/edit-course.component';
import { TeacherHomeComponent } from './teacher-home/teacher-home.component';
import { CourseDetailsComponent } from './course-details/course-details.component';
import { CourseAbsencesComponent } from './course-absences/course-absences.component';
import { CourseStudentsComponent } from './course-students/course-students.component';
import { CourseLessonsComponent } from './course-lessons/course-lessons.component';
import { CourseTestsComponent } from './course-tests/course-tests.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    IndexComponent,
    HomeComponent,
    CreateCourseComponent,
    EditCourseComponent,
    TeacherHomeComponent,
    CourseDetailsComponent,
    CourseAbsencesComponent,
    CourseStudentsComponent,
    CourseLessonsComponent,
    CourseTestsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [
    AuthenticationService,
    PathService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
