import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  todos = [];
  edit: boolean = false;
  contentInput: String = '';
  error: string = '';

  constructor(private http: HttpClient) {}

  getAll(){
      this.http.get<any>("/todos").subscribe(resp => {
          this.todos = resp;
          this.todos.reverse();
      }
      );
      return this.todos;
  }

  completed(t, event){
      if(event.target.nodeName != 'P'){
          if(!t.completed){
            this.http.patch("/complete/" + t.id, null).subscribe();
            t.completed = true;
          }
          else{
            this.http.patch("/uncomplete/" + t.id, null).subscribe();
            t.completed = false;
          }
      }
  }

  postTodo(){
      if(this.contentInput.length == 0){
          this.error = "Todo can't be empty!";
          return;
        }
      else this.error = '';
      this.http.post<any>("/create", this.contentInput).subscribe(resp => {
      if(resp != null){
          this.error = '';
          this.todos.unshift(resp);
      }
      else
          this.error = "Max size is 50!";
      });
      this.contentInput = '';
  }

  doneEditing(t, event){
      event.preventDefault();
      let prev = t.cont;
      if(event.target.innerText.trim().length === 0){
        event.target.innerText = "Todo can't be empty!";
        event.target.style.color = "red";
        setTimeout(function(){
          event.target.innerText = t.cont;
          event.target.style.color = "#464646";
        }, 1500);
        return;
      }
      if(this.edit){
        this.edit = false;
        let text = event.target.innerText;
        if(prev == text)
          return;
        this.http.put("/edit/" + t.id, event.target.innerText, {responseType: 'text'}).subscribe(resp => {
          if(resp != null){
            event.target.innerText = "Max size is 50!";
            event.target.style.color = "red";
            setTimeout(function(){
              event.target.innerText = prev;
              event.target.style.color = "#464646";
            }, 1500);
          }
          else{
            event.target.innerText = "Todo updated!";
            event.target.style.color = "lawngreen";
            setTimeout(function(){
              event.target.innerText = text;
              t.cont = text;
              event.target.style.color = "#464646";
            }, 1500);
          }
        });
      }
  }

  remove(t){
      this.http.delete("/remove/" + t.id).subscribe();
      this.todos.splice(this.todos.indexOf(t),1);
  }

  check(){
      if(this.contentInput.length > 50)
      this.error = "Max size is 50!";
    else
      this.error = '';
  }

  doneIcon(t){
    return t.completed ? 'green' : '#ededed';
  }

  doneText(t){
    return t.completed ? 'line-through' : 'none';
  }

}
