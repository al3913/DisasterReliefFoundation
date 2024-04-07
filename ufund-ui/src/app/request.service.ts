import { Injectable } from '@angular/core';
import { HelpRequest } from './request'; 
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from './message.service';
import { Observable, catchError, of, tap } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
    providedIn: 'root'
  })
export class RequestService{

    private requestUrl = 'http://localhost:8080/mailbox';

    constructor(private http: HttpClient, private messageService: MessageService, private loginService : LoginService){}
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
      };


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
        this.messageService.add(`RequestService: ${message}`);
    }

    addRequest(request : HelpRequest): Observable<HelpRequest> {
      request.creator = this.loginService.getWhoYouAre();
        return this.http.post<HelpRequest>(this.requestUrl, request, this.httpOptions).pipe(
          tap((newRequest: HelpRequest) => this.log(`added request w/ id=${newRequest.id}`)),
          catchError(this.handleError<HelpRequest>('addHelpRequest'))
        );
    }
    getRequests() : Observable<HelpRequest[]> {
        return this.http.get<HelpRequest[]>(this.requestUrl)
        .pipe(
          tap(_ => this.log('fetched requests')),
          catchError(this.handleError<HelpRequest[]>('getRequests', []))
          );
    }
    getRequest(id : number) : Observable<HelpRequest> {
      const url = `${this.requestUrl}/${id}`;
      return this.http.get<HelpRequest>(url).pipe(
        tap(_ => this.log(`fetched request id=${id}`)),
        catchError(this.handleError<HelpRequest>(`getRequest id=${id}`))
      );
    }

    deleteRequest(id: number): Observable<HelpRequest> {
      const url = `${this.requestUrl}/${id}`;
  
      return this.http.delete<HelpRequest>(url, this.httpOptions).pipe(
        tap(_ => this.log(`deleted request id=${id}`)),
        catchError(this.handleError<HelpRequest>('deleteNeed'))
      );
    }

}