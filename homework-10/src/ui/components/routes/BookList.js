import React, { useState, useEffect } from 'react';
import {Link} from "react-router-dom";
import {ADD_NEW_BOOK} from "./BookEdit";


function BookList() {

    const [books, setBooks] = useState([]);

    useEffect(() => {
        fetch('/api/books')
            .then(response => response.json())
            .then(books => setBooks(books));
    }, []);

    const remove = (id) => {
        fetch(`/api/books/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(() => {
            let updatedBooks = [...books].filter(book => book.id !== id);
            setBooks(updatedBooks);
        })
    };

    return (
        <div>
            <h1>Books ib library</h1>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Author</th>
                    <th>Genre</th>
                </tr>
                </thead>
                <tbody>
                {
                    books.map((book, index) => (
                        <tr key={index}>
                            <td>{book.id}</td>
                            <td>{book.title}</td>
                            <td>{book.author}</td>
                            <td>{book.genre}</td>
                            <td><Link to={`/books/${book.id}`}><button>Edit</button></Link> </td>
                            <td><button onClick={() => remove(book.id)}>Remove</button> </td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
            <Link to={`/books/${ADD_NEW_BOOK}`}>Add new Book</Link>
        </div>
    )
}

export default BookList;