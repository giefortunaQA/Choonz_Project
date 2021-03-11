'use strict';
// Global vars
const pageTitle = document.querySelector("title");

// Paths
const artistsPath = "artists";
const tracksPath = "tracks";
const albumsPath = "albums";
const genresPath = "genres";
const playlistsPath = "playlists";

// Containers (output)
const tracksContainer = document.getElementById("tracksContainer")
const artistsContainer = document.getElementById("artistsContainer")
const genresContainer = document.getElementById("genresContainer")
const playlistsContainer = document.getElementById("playlistsContainer")
const albumsByArtistDiv = document.getElementById("albumsByArtistDiv")
const tracksByArtistDiv = document.getElementById("trackByArtistDiv")

//Other Outputs
const createdArtistMsg = document.getElementById("createdArtistMsg")
const artistNameDisplay = document.getElementById("artistNameDisplay")
const genreNameDisplay = document.getElementById("genreNameDisplay")
const genreDescDisplay = document.getElementById("genreDescDisplay")
const albumsByArtistTitle = document.getElementById("albumsByArtistTitle")
const trackNameDisplay = document.getElementById("trackNameDisplay")
const trackLyricsDisplay = document.getElementById("trackLyricsDisplay")
const eachTrackDiv = document.getElementById("eachTrackDiv");
const tracksByArtistTitle = document.getElementById("tracksByArtistTitle")
const artistChild=document.querySelectorAll(".artist-child")

// Form Divs
const createArtistForm = document.getElementById("createArtistForm")
const createGenreForm = document.getElementById("createGenreForm")
const updateArtistForm = document.getElementById("updateArtistForm")

// Buttons
let updateEachArtist = document.getElementById("updateEachArtist")
let updateArtistBtn = document.getElementById("updateArtistBtn")
let deleteEachAlbum = document.getElementById("deleteEachAlbum")
let updateEachGenre = document.getElementById("updateEachGenre")

// Inputs
const artistName = document.getElementById("artistName")
const genreName = document.getElementById("genreName")
const artistNameUpdate = document.getElementById("artistNameUpdate")
const genreDesc = document.getElementById("genreDesc")
const albumName = document.getElementById("albumName")
const albumCover = document.getElementById("albumCover")
const album_artistId = document.getElementById("album_artistId")
const album_genreId = document.getElementById("album_genreId")
const playlistName = document.getElementById("playlistName")
const playlistArtwork = document.getElementById("playlistArtwork")
const playlistDesc = document.getElementById("playlistDesc")
const playlistUser = document.getElementById("playlistUser")
const playlistNameUpdate = document.getElementById("playlistNameUpdate")
const playlistArtworkUpdate = document.getElementById("playlistArtworkUpdate")
const playlistDescUpdate = document.getElementById("playlistDescUpdate")
const playlistUserUpdate = document.getElementById("playlistUserUpdate")
const trackName = document.getElementById("trackName")
const trackLyrics = document.getElementById("trackLyrics")
const trackDuration = document.getElementById("trackDuration")
const trackAlbum = document.getElementById("trackAlbum")
const trackPlaylist = document.getElementById("trackPlaylist")
const trackNameUpdate = document.getElementById("trackNameUpdate")
const trackLyricsUpdate = document.getElementById("trackLyricsUpdate")
const trackDurationUpdate = document.getElementById("trackDurationUpdate")
const trackAlbumUpdate = document.getElementById("trackAlbumUpdate")
const trackPlaylistUpdate = document.getElementById("trackPlaylistUpdate")


// Variables for navigation
const root = "file:///C:/Users/giean/Documents/Workspaces/VS%20-%20Workspace/Choonz/static";
const params = new URLSearchParams(window.location.search);

//#################################################################################################################
//#################################################################################################################
function noRecordsMsg(path, container) {
    let italic = document.createElement("i");
    let text = document.createTextNode(`There are no ${path} in the system yet. Sorry.`)
    italic.appendChild(text);
    container.appendChild(italic);
}
// MAKING INDIVIDUAL CARDS- COMPONENTS
// CARD DIV
function createCardDiv(path, data) {
    let card = document.createElement("div");
    card.id = `${path}${data.id}`;
    card.setAttribute("class", "card col-2");
    card.appendChild(document.createElement("br"));
    return card;
}
//CARD IMG TOP
function createCardImg(url) {
    let img = document.createElement("img");
    img.setAttribute("src", url);
    img.setAttribute("class", "card-img-top");
    return img;
}
//CARD TITLE
function createCardTitle(title) {
    let cardTitle = document.createElement("button");
    cardTitle.setAttribute("class", "card-title");
    let titleOut = document.createElement("h3");
    titleOut.appendChild(document.createTextNode(title));
    cardTitle.appendChild(titleOut);
    cardTitle.setAttribute("class", "btn link");
    return cardTitle;
}

//CARD SUBTITLE
function createCardSubtitle(string) {
    let cardSub = document.createElement("h6");
    cardSub.setAttribute("class", "card-subtitle mb-2 text-muted")
    cardSub.innerHTML = string;
    return cardSub;
}

//CARD TEXT
function createCardText(string) {
    let cardText = document.createElement("p");
    cardText.setAttribute("class", "card-text");
    cardText.innerHTML = string;
    return cardText;
}

//CARD LINKS
function createCardLink(href, text) {
    let cardLink = document.createElement("a");
    cardLink.setAttribute("class", "card-link");
    cardLink.setAttribute("href", href);
    cardLink.innerHTML = text;
    return cardLink;
}

function show(target) {
    target.style.display = "block";
}
function hide(target) {
    target.style.display = "none";
}

//#################################################################################################################
//#################################################################################################################
// METHODS FOR ARTIST
function createArtistCard(display, artist) {
    let artistCard = createCardDiv(artistsPath, artist);
    let artistName = createCardTitle(artist.name);
    artistCard.appendChild(artistName);
    artistCard.setAttribute("onclick", `goTo(artistsPath,${artist.id})`)
    display.appendChild(artistCard);
}

function createArtist() {
    let formData = {
        "name": artistName.value
    }
    fetch("http://localhost:8082/artists/create", {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(formData)
    })
        .then(res => res.json())
        .then(data => {
            console.log(`Request succeeded with JSON response ${data}`);
            hide(createArtistForm);
            show(createdArtistMsg);
            setTimeout(hide(createdArtistMsg), 3000);
            location.reload();
        })
        .catch((err) => console.log(err))
}
function readArtistById(id) {
    fetch(`http://localhost:8082/artists/read/${id}`)
        .then((res) => {
            if (res.ok != true) {
                console.log("Status is not OK!");
            }
            res.json()
                .then((data) => {
                    let title = document.createElement("h1");
                    title.innerHTML = "This is: " + data.name;
                    artistNameDisplay.appendChild(title);
                    updateArtistBtn.setAttribute("onclick", `updateArtist(${data.id})`);
                    deleteEachArtist.setAttribute("onclick", `confirmDeleteArtist(${data.id})`);
                    pageTitle.innerHTML = data.name;
                    readAlbumsByArtist(data.id);
                    readTracksByArtist(data.id);
                    albumsByArtistTitle.innerHTML = `Albums by ${data.name}:`;
                    tracksByArtistTitle.innerHTML = `Songs by ${data.name}`;
                }).catch((err) => console.log(err))
        })
}

function readArtistPageLoad() {
    readArtistById(params.get('id'));
}
function readAlbumsByArtist(id) {
    fetch(`http://localhost:8082/albums/read/by-artist/${id}`)
        .then((res) => {
            if (res.ok != true) {
                console.log("Status is not OK!");
            }
            res.json()
                .then((data) => {
                    for (let album of data) {
                        createAlbumCard(albumsByArtistDiv, album);
                    }
                }).catch((err) => console.log(err))
        })
}
function readTracksByArtist(id) {
    fetch(`http://localhost:8082/tracks/read/by-artist/${id}`)
        .then((res) => {
            if (res.ok != true) {
                console.log("Status is not OK!");
            }
            res.json()
                .then((data) => {
                    for (let track of data) {
                        createTrackCard(tracksByArtistDiv, track);
                    }
                }).catch((err) => console.log(err))
        })
}

function updateArtist(id) {
    let formData = {
        "name": artistNameUpdate.value
    }
    fetch(`http://localhost:8082/artists/update/${id}`, {
        method: 'put',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(formData)
    })
        .then(res => res.json())
        .then(data => {
            console.log(`Request succeeded with JSON response ${data}`);
            location.reload();
        })
        .catch((err) => console.log(err))
}

function deleteArtist(id) {
    fetch(`http://localhost:8082/artists/delete/${id}`, {
        method: 'delete',
    })
        .then(res => res.json())
        .then(data => {
            console.log(`Request succeeded with JSON response ${data}`);
            window.location.replace(`${root}/readArtist.html#deleted`);
               
            })
        .catch((err) => console.log(err))
}

function confirmDeleteArtist(id) {
    if (confirm("Are you sure?")) {
        deleteArtist(id);
        artistNameDisplay.innerHTML = "Artist deleted."
        for (let child of artistChild){
            hide(child);
        }
    }
}
//#################################################################################################################
//#################################################################################################################
// METHODS FOR GENRE 
function createGenreCard(display, genre) {
    let genreCard = createCardDiv(genresPath, genre);
    let genreTitle = createCardTitle(genre.name);
    genreCard.appendChild(genreTitle);
    genreCard.setAttribute("onclick", `goTo(genresPath,${genre.id})`);
    display.appendChild(genreCard);
}


function createGenre() {
    let formData = {
        "name": genreName.value,
        "description": genreDesc.value
    }
    fetch("http://localhost:8082/genres/create", {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(formData)
    })
        .then(res => res.json())
        .then(data => {
            console.log(`Request succeeded with JSON response ${data}`);
            hide(createGenreForm);
            show(createdGenreMsg);
            setTimeout(hide(createdGenreMsg), 3000);
            location.reload();
        })
        .catch((err) => console.log(err))
}

function readGenreById(id) {
    fetch(`http://localhost:8082/genres/read/${id}`)
        .then((res) => {
            if (res.ok != true) {
                console.log("Status is not OK!");
            }
            res.json()
                .then((data) => {
                    let title = document.createElement("h1");
                    title.innerHTML = data.name;
                    let desc = document.createElement("h4");
                    desc.innerHTML = data.description;
                    genreNameDisplay.appendChild(title);
                    genreDescDisplay.appendChild(desc);
                    updateGenreBtn.setAttribute("onclick", `updateGenre(${data.id})`);
                    deleteEachGenre.setAttribute("onclick", `confirmDeleteGenre(${data.id})`);
                    pageTitle.innerHTML=data.name;
                }).catch((err) => console.log(err))
        })
}
function readGenrePageLoad() {
    readGenreById(params.get('id'));
}

function updateGenre(id) {
    let formData = {
        "name": genreNameUpdate.value,
        "description": genreDescUpdate.value
    }
    fetch(`http://localhost:8082/genres/update/${id}`, {
        method: 'put',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(formData)
    })
        .then(res => res.json())
        .then(data => {
            console.log(`Request succeeded with JSON response ${data}`);
            location.reload();
        })
        .catch((err) => console.log(err))
}

function deleteGenre(id) {
    fetch(`http://localhost:8082/genres/delete/${id}`, {
        method: 'delete',
    })
        .then(res => JSON.stringify(res))
        .then(data => {
            console.log(`Request succeeded with JSON response ${data}`);
            window.location.replace(`${root}/readGenre.html#deleted`);
        })
        .catch((err) => console.log(err))
}

function confirmDeleteGenre(id) {
    if (confirm("Are you sure?")) {
        deleteGenre(id);
        genreNameDisplay.innerHTML = "Genre deleted.";
        genreDescDisplay.innerHTML="";
    }
}
//#################################################################################################################
//#################################################################################################################
// METHODS FOR ALL ENTITIES
function goTo(path, id) {
    if (path == tracksPath) {
        window.location.replace(`${root}/readTrack.html?id=${id}`);
    } else if (path == artistsPath) {
        window.location.replace(`${root}/readArtist.html?id=${id}`);
    } else if (path == genresPath) {
        window.location.replace(`${root}/readGenre.html?id=${id}`);
    } else if (path == albumsPath) {
        window.location.replace(`${root}/readAlbum.html?id=${id}`);
    } else if (path == playlistsPath) {
        window.location.replace(`${root}/readPlaylist.html?id=${id}`);
    }

}

const readPage = (path) => {
    fetch(`http://localhost:8082/${path}/read`)
        .then((res) => {
            if (res.ok != true) {
                console.log("Status is not OK!");
            }
            res.json()
                .then((data) => {
                    if (path == tracksPath) {
                        if (data.length == 0) {
                            noRecordsMsg(tracksPath, tracksContainer);
                        }
                        else {
                            for (let track of data) {
                                createTrackCard(tracksContainer, track)
                            }
                        }
                    } else if (path == artistsPath) {
                        if (data.length == 0) {
                            noRecordsMsg(artistsPath, artistsContainer);
                        } else {
                            for (let artist of data) {
                                createArtistCard(artistsContainer, artist)
                            }
                        }
                    } else if (path == genresPath) {
                        if (data.length == 0) {
                            noRecordsMsg(genresPath, genresContainer);
                        } else {
                            for (let genre of data) {
                                createGenreCard(genresContainer, genre)
                            }
                        }
                    } else if (path == albumsPath) {
                        if (data.length == 0) {
                            noRecordsMsg(albumsPath, albumsContainer);
                        } else {
                            for (let album of data) {
                                createAlbumCard(albumsContainer, album)
                            }
                        }
                    } else if (path == playlistsPath) {
                        if (data.length == 0) {
                            noRecordsMsg(path, playlistsContainer);
                        } else {
                            for (let playlist of data) {
                                createPlaylistCard(playlistsContainer, playlist)
                            }
                        }
                    }
                    console.log(`${path} page loaded.`);
                }).catch((err) => console.log(err))
        })
}

