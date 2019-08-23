import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessedOrdersComponent } from './processed-orders.component';

describe('ProcessedOrdersComponent', () => {
  let component: ProcessedOrdersComponent;
  let fixture: ComponentFixture<ProcessedOrdersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessedOrdersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessedOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
