import { LayoutModule } from '@angular/cdk/layout';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { waitForAsync, ComponentFixture, TestBed } from '@angular/core/testing';


import { PlaceOrderComponent } from './place-order.component';
import { MaterialModule } from 'src/app/material/material.module';

describe('PlaceOrderComponent', () => {
  let component: PlaceOrderComponent;
  let fixture: ComponentFixture<PlaceOrderComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [PlaceOrderComponent],
      imports: [
        NoopAnimationsModule,
        LayoutModule,
        MaterialModule
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlaceOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
