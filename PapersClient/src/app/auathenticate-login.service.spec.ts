import { TestBed } from '@angular/core/testing';

import { AuathenticateLoginService } from './auathenticate-login.service';

describe('AuathenticateLoginService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuathenticateLoginService = TestBed.get(AuathenticateLoginService);
    expect(service).toBeTruthy();
  });
});
