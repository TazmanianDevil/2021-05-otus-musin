import React, {useEffect, useState} from 'react';
import {Link, useHistory, useParams} from 'react-router-dom';

export const ADD_NEW_BOOK = 'add-new';


function BookEdit() {
    let {id} = useParams();
    const [book, setBook] = useState({
        id: '',
        title: '',
        author: {},
        genre: {}
    });

    const [genres, setGenres] = useState([]);

    const history = useHistory();

    function newBook() {
        return book.id === undefined;
    }


    useEffect(() => {
        fetch('/api/genres')
            .then(response => response.json())
            .then(genres => {
                if (genres.length > 0) {
                    setBook({
                        ...book, genre: {
                            genre: genres[0]
                        }
                    });
                }
                setGenres(genres);
            });
    }, []);

    useEffect(() => {
        if (id !== undefined && id !== ADD_NEW_BOOK) {
            fetch(`/api/books/${id}`)
                .then(response => {
                    return response.json()
                })
                .then(book => {
                    console.log('Book received: ', book);
                    setBook(book);
                });
        }
    }, []);

    const changeTitle = (event) => {
        const val = event.target.value;
        setBook({...book, title: val});
    };

    const changeGenre = (event) => {
        const val = event.target.value;
        setBook({
            ...book, genre: {
                name: val
            }
        });
    };

    const changeAuthor = (event) => {
        const val = event.target.value;
        setBook({
            ...book, author: {
                fullName: val
            }
        });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('/api/books', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        })
            .then(() => {
                history.push("/");
            })
    };

    const renderSelect = () => {
        return <select onChange={changeGenre} value={book.genre.name}>
            {genres.map((value, index) => {
                return <option value={value.name}>{value.name}</option>
            })}
        </select>
    };

    return (
        <div>
            <h1>{newBook() ? 'Add new book' : 'Edit book'}</h1>
            <form onSubmit={handleSubmit}>
                <label>Title: </label>
                <input type={"text"} name={"title"} value={book.title} onChange={changeTitle}/>
                <br/>
                <label>Genre</label>

                {renderSelect()}
                <br/>
                <label>Author:</label>
                <input type={"text"} name={"author"} value={book.author.fullName} onChange={changeAuthor}/>
                <br/>
                <input type={"submit"} title={"Save"}/>
            </form>
            <Link to={"/"}>Cancel</Link>
        </div>
    )
}

export default BookEdit;