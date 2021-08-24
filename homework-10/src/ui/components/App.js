import React from 'react';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import BookList from "./routes/BookList";
import SaveBook from "./routes/SaveBook";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Switch>
                    <Route path={"/"} exact>
                        <BookList/>
                    </Route>
                    <Route path={"/saveBook"}>
                        <SaveBook/>
                    </Route>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
