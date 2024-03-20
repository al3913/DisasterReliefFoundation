import { Injectable } from '@angular/core';
import { LoginComponent } from './login/login.component';
import { Observable, of } from 'rxjs';
import { User } from './user';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private usersURL = 'http://localhost:8080/users';
  constructor(private http: HttpClient, private messageService: MessageService) {}
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  getUser(id : number) : Observable<User> {
    const url = `${this.usersURL}/${id}`;
    return this.http.get<User>(this.usersURL)
    .pipe(
      tap(_ => this.log('fetched user')),
      catchError(this.handleError<User>('getUser'))
      );
  }
  
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  private log(message: string) {
    this.messageService.add(`NeedService: ${message}`);
  }
  
  updateUser(user: User): Observable<any> {
    return this.http.put(this.usersURL, user, this.httpOptions).pipe(
      tap(_ => this.log(`updated need id=${user.id}`)),
      catchError(this.handleError<any>('updateNeed'))
    );
  }
}