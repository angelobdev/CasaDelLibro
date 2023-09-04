import { TestBed } from '@angular/core/testing';

import { SpedizioneService } from './spedizione.service';

describe('SpedizioneService', () => {
  let service: SpedizioneService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpedizioneService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
