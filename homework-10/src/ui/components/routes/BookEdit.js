import React, {useEffect, useState} from 'react';
import {Link, useParams} from 'react-router-dom';

export const ADD_NEW_BOOK = 'add-new';


function BookEdit() {
    let {id} = useParams();
    const [book, setBook] = useState({
        id: '',
        title: '',
        author: '',
        genre: ''
    });

    const [genres, setGenres] = useState([]);

    function newBook() {
        return book.id === undefined;
    }

    useEffect(() => {
        if (id !== undefined && id !== ADD_NEW_BOOK) {
            fetch(`/api/books/${id}`)
                .then(response => {
                    return response.json()
                })
                .then(book => setBook(book));
        }
    }, []);

    useEffect(() => {
        fetch('/api/genres')
            .then(response => response.json())
            .then(genres => {
                console.log(genres);
                setGenres(genres);
            });
    }, []);


    const changeTitle = (event) => {
        const val = event.target.value;
        setBook({...book, title: val});
    };

    const changeGenre = (event) => {
        const val = event.target.value;
        console.log(event, val);
        setBook({...book, genre: val});
    };

    return (
        <div>
            <h1>{newBook() ? 'Add new book' : 'Edit book'}</h1>
            <form>
                <label>Title: </label>
                <input type={"text"} name={"title"} value={book.title} onChange={changeTitle}/>
                <br/>
                <label>Genre</label>
                <select onChange={changeGenre}>
                    {genres.map(((value, index) => {
                        return <option value={value.genre} selected={value.genre === book.genre}>{value.genre}</option>
                    }))}
                </select>
            </form>
            <Link to={"/"}>Cancel</Link>
        </div>
    )
}

export default BookEdit;