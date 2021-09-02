import React from 'react';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import BookList from "./routes/BookList";
import BookEdit from "./routes/BookEdit";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Switch>
                    <Route path={"/"} exact>
                        <BookList/>
                    </Route>
                    <Route path={"/books/:id"}>
                        <BookEdit/>
                    </Route>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
