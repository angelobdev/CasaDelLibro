import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiziComponent } from './servizi.component';

describe('ServiziComponent', () => {
  let component: ServiziComponent;
  let fixture: ComponentFixture<ServiziComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ServiziComponent]
    });
    fixture = TestBed.createComponent(ServiziComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
