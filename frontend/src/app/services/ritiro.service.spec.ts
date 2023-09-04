import { TestBed } from '@angular/core/testing';

import { RitiroService } from './ritiro.service';

describe('RitiroService', () => {
  let service: RitiroService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RitiroService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
