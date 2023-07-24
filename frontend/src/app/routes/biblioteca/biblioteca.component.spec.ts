import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BibliotecaComponent } from './biblioteca.component';

describe('BibliotecaComponent', () => {
  let component: BibliotecaComponent;
  let fixture: ComponentFixture<BibliotecaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BibliotecaComponent]
    });
    fixture = TestBed.createComponent(BibliotecaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
