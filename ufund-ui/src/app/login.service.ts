import { Injectable } from '@angular/core';
import { LoginComponent } from './login/login.component';
import { Observable, of, pipe } from 'rxjs';
import { User } from './user';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private usersURL = 'http://localhost:8080/users';
  username :string = "";
  x : string | null = null;
  
  constructor(private http: HttpClient, private messageService: MessageService) {}
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  

  getWhoYouAre(){
    this.x  = localStorage.getItem("user");
    if(this.x == null){
      return "";
    }
    return this.x;
  }





  // Added getUser with Username
  getUser(id : number) : Observable<User>;
  getUser(username : string) : Observable<User>;

  getUser(input : any) : Observable<User> {
    if (typeof input === "number")
    {
      const url = `${this.usersURL}/${input}`;
      return this.http.get<User>(this.usersURL)
      .pipe(
        tap(_ => this.log('fetched user')),
        catchError(this.handleError<User>('getUser'))
        );
    }
    else{
      return this.http.get<User>(`${this.usersURL}/?username=${input}`)
      .pipe(
        tap(_ => this.log('fetched user')),
        catchError(this.handleError<User>('getUser'))
        );
    }
  }
  // Added getUser with Username ^^^^

  getUsers() : Observable<User[]> {
    return this.http.get<User[]>(this.usersURL)
    .pipe(
      tap(_ => this.log('fetched users')),
      catchError(this.handleError<User[]>('getUsers', []))
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
    this.messageService.add(`LoginService: ${message}`);
  }

  getUsername () : string { 
    return this.username;
  }
  setUsername (username : string) : void {
    this.username = username;
  }
  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.usersURL, user, this.httpOptions).pipe(
      tap((newUser: User) => this.log(`added user w/ id=${newUser.id}`)),
      catchError(this.handleError<User>('addUser'))
    );
  }
  logout(){
    this.logout;
  }
}