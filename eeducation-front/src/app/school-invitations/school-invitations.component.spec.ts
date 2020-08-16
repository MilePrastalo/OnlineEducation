import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchoolInvitationsComponent } from './school-invitations.component';

describe('SchoolInvitationsComponent', () => {
  let component: SchoolInvitationsComponent;
  let fixture: ComponentFixture<SchoolInvitationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchoolInvitationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchoolInvitationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
