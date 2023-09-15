import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CycleService {

  constructor(private http: HttpClient) { }

  // Define a method to get cycles from the Spring Boot backend
  getAllCycles(): Observable<any[]> {
    return this.http.get<any[]>('/api/cycles');
  }

  // Define methods to borrow and return cycles
  borrowCycle(cycleId: number, count: number): Observable<any> {
    return this.http.get<any>(`/api/cycles/${cycleId}/borrow?count=${count}`);
  }

  returnCycle(cycleId: number, count: number): Observable<any> {
    return this.http.get<any>(`/api/cycles/${cycleId}/return?count=${count}`);
  }
  
}
