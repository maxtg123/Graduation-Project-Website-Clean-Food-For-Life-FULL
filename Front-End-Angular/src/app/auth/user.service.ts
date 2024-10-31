import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/profile';

  constructor(private http: HttpClient) {}

  getUserById(id: string): Observable<User> { // Sử dụng kiểu User
    return this.http.get<User>(`${this.apiUrl}/${id}`);
  }

  updateUser(id: string, userUpdateRequest: User): Observable<User> { // Sử dụng kiểu User
    return this.http.put<User>(`${this.apiUrl}/${id}`, userUpdateRequest);
  }

  updateUserAvatar(id: string | null, formData: File): Observable<User> {
    return this.http.post<User>(`/api/users/${id}/avatar`, formData);
  }


}
