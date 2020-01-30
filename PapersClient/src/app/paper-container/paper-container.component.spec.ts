import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaperContainerComponent } from './paper-container.component';

describe('PaperContainerComponent', () => {
  let component: PaperContainerComponent;
  let fixture: ComponentFixture<PaperContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaperContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaperContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
