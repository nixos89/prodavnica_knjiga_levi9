import { TestBed } from '@angular/core/testing';

import { AdminManagementService } from './admin-management.service';

describe('AdminManagementService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdminManagementService = TestBed.get(AdminManagementService);
    expect(service).toBeTruthy();
  });
});
