'use strict';
// Global vars
const pageTitle = document.querySelector("title");
const greet = document.getElementById("greet")
const btns = document.querySelectorAll(".crud-btn")
const body=document.querySelector("body");
var userId=parseInt(window.localStorage.getItem("userId"));
var sessionToken=window.localStorage.getItem("token");
var currentUser=window.localStorage.getItem("currentUser");
var status= window.localStorage.getItem("userStatus");
const forms=document.querySelectorAll("input");
// Variables for navigation
const root = "http://localhost:8082";
const params = new URLSearchParams(window.location.search);
// Paths
const artistsPath = "artists";
const tracksPath = "tracks";
const albumsPath = "albums";
const genresPath = "genres";
const playlistsPath = "playlists";
const usersPath = "user"

// Containers (output)
const tracksContainer = document.getElementById("tracksContainer")
const artistsContainer = document.getElementById("artistsContainer")
const genresContainer = document.getElementById("genresContainer")
const playlistsContainer = document.getElementById("playlistsContainer")
const albumsByArtistDiv = document.getElementById("albumsByArtistDiv")
const tracksByArtistDiv = document.getElementById("tracksByArtistDiv")
const tracksInAlbumDiv = document.getElementById("tracksInAlbumDiv")
const tracksInPlaylistDiv = document.getElementById("tracksInPlaylistDiv")
const playlistsInGenreDiv = document.getElementById("playlistsInGenreDiv")
const albumsInGenreDiv = document.getElementById("albumsInGenreDiv")
const tracksInGenreDiv = document.getElementById("tracksInGenreDiv")

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
const tracksInAlbumTitle = document.getElementById("tracksInAlbumTitle")
const tracksInPlaylistTitle = document.getElementById("tracksInPlaylistTitle")
const playlistsInGenreTitle = document.getElementById("playlistsInGenreTitle")
const albumsInGenreTitle = document.getElementById("albumsInGenreTitle")
const artistChild = document.querySelectorAll(".artist-child")
const userChild=document.querySelectorAll(".user-child")
const userDisplay = document.getElementById("userDisplay")
const userTitle = document.getElementById("userTitle")
const userPlaylists = document.getElementById("userPlaylists")
const userMsg=document.getElementById("userMsg")
const notNav=document.querySelector("nav+div")
// Form Divs
const createArtistForm = document.getElementById("createArtistForm")
const createGenreForm = document.getElementById("createGenreForm")
const updateArtistForm = document.getElementById("updateArtistForm")
const signInForm = document.getElementById("signInForm")
const signUpForm = document.getElementById("signUpForm")

// Buttons
const updateEachArtist = document.getElementById("updateEachArtist")
const updateArtistBtn = document.getElementById("updateArtistBtn")
const deleteEachAlbum = document.getElementById("deleteEachAlbum")
const updateEachGenre = document.getElementById("updateEachGenre")
const updateUserBtn=document.getElementById("updateUserBtn")
const deleteUserBtn=document.getElementById("deleteUserBtn")

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
const usernameUp = document.getElementById("usernameUp")
const passwordUp = document.getElementById("passwordUp")
const usernameIn = document.getElementById("usernameIn")
const passwordIn = document.getElementById("passwordIn")
const usernameUpdate = document.getElementById("usernameUpdate")
const passwordUpdate = document.getElementById("passwordUpdate")
const updateUserForm=document.getElementById("updateUserForm")


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

function userFormToggle(target) {
	if (target == signUpForm) {
		show(signUpForm);
		hide(signInForm);
	} else {
		hide(signUpForm);
		show(signInForm);
	}
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
			console.log(`Artist created. ${data}`);
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
					console.log(`Artist retrieved. ${data}`);
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
	greetUser();
}
function readAlbumsByArtist(id) {
	fetch(`http://localhost:8082/albums/read/by-artist/${id}`)
		.then((res) => {
			if (res.ok != true) {
				console.log("Status is not OK!");
			}
			res.json()
				.then((data) => {
					console.log(`Albums by artist retrieved. ${data}`);
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
					console.log(`Tracks by artist retrieved. ${data}`);
					for (let track of data) {
						createTrackCard(tracksByArtistDiv, track);
						console.log(track);
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
			console.log(`Artist updated. ${data}`);
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
			console.log(`Artist deleted`);
			console.log(`Request succeeded with JSON response ${data}`);
			window.location.replace(`${root}/readArtist.html#deleted`);

		})
		.catch((err) => console.log(err))
}

function confirmDeleteArtist(id) {
	if (confirm("Are you sure?")) {
		deleteArtist(id);
		artistNameDisplay.innerHTML = "Artist deleted."
		for (let child of artistChild) {
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
			console.log(`Genre created. ${data}`);
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
					console.log(`Genre retrieved. ${data}`);
					let title = document.createElement("h1");
					title.innerHTML = data.name;
					let desc = document.createElement("h4");
					desc.innerHTML = data.description;
					genreNameDisplay.appendChild(title);
					genreDescDisplay.appendChild(desc);
					updateGenreBtn.setAttribute("onclick", `updateGenre(${data.id})`);
					deleteEachGenre.setAttribute("onclick", `confirmDeleteGenre(${data.id})`);
					pageTitle.innerHTML = data.name;
					readAlbumsInGenre(data.id);
					readTracksInGenre(data.id);
				}).catch((err) => console.log(err))
		})
}



function readAlbumsInGenre(id) {
	fetch(`http://localhost:8082/albums/read/by-genre/${id}`)
		.then((res) => {
			if (res.ok != true) {
				console.log("Status is not OK!");
			}
			res.json()
				.then((data) => {
					for (let album of data) {
						console.log(`Albums in genre. ${data}`);
						createAlbumCard(albumsInGenreDiv, album);
						console.log(album);
					}
				}).catch((err) => console.log(err))
		})
}

function readTracksInGenre(id) {
	fetch(`http://localhost:8082/tracks/read/by-genre/${id}`)
		.then((res) => {
			if (res.ok != true) {
				console.log("Status is not OK!");
			}
			res.json()
				.then((data) => {
					for (let track of data) {
						console.log(`Tracks in genre. ${data}`);
						createTrackCard(tracksInGenreDiv, track);
						console.log(track);
					}
				}).catch((err) => console.log(err))
		})
}
function readGenrePageLoad() {
	readGenreById(params.get('id'));
	greetUser();
}

function updateGenre(id) {
	let formData = {}
	if (genreNameUpdate.value!=""){
		formData.name=genreNameUpdate.value;
	}
	console.log(formData);
	if (genreDescUpdate.value!=""){
		formData.description=genreDescUpdate.value;
	}
	console.log(formData);
	fetch(`http://localhost:8082/genres/update/${id}`, {
		method: 'put',
		headers: {
			"Content-type": "application/json"
		},
		body: JSON.stringify(formData)
	})
		.then(res => res.json())
		.then(data => {
			console.log(`Genre updated. ${data}`);
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
			console.log(`Genre deleted. ${data}`);
			console.log(`Request succeeded with JSON response ${data}`);
			window.location.replace(`${root}/readGenre.html#deleted`);
		})
		.catch((err) => console.log(err))
}

function confirmDeleteGenre(id) {
	if (confirm("Are you sure?")) {
		deleteGenre(id);
		genreNameDisplay.innerHTML = "Genre deleted.";
		genreDescDisplay.innerHTML = "";
	}
}
//#################################################################################################################
//#################################################################################################################
// METHODS FOR ALBUM 
function createAlbumCard(display, album) {
	let albumCard = createCardDiv(albumsPath, album);
	let albumTitle = createCardTitle(album.name);
	let albumCover = createCardImg(album.cover);
	let albumArtist = createCardLink(`${root}/readArtist.html?id=${album.artist.id}`, album.artist.name);
	let albumGenre = createCardLink(`${root}/readGenre.html?id=${album.genre.id}`, album.genre.name);
	let albumLinks = document.createElement("div");
	albumLinks.appendChild(albumArtist);
	albumLinks.appendChild(albumGenre);
	albumCard.appendChild(albumCover);
	albumCard.appendChild(albumTitle);
	albumCard.appendChild(albumLinks);
	albumCard.setAttribute("onclick", `goTo(albumsPath,${album.id})`);
	display.appendChild(albumCard);
	console.log(album);
	console.log(albumCard);
}


function createAlbum() {
	let formData = {
		"name": albumName.value,
		"cover": albumCover.value,
		"artist": {
			"id": album_artistId.value
		},
		"genre": {
			"id": album_genreId.value
		}
	}
	fetch("http://localhost:8082/albums/create", {
		method: 'post',
		headers: {
			"Content-type": "application/json"
		},
		body: JSON.stringify(formData)
	})
		.then(res => res.json())
		.then(data => {
			console.log(`Request succeeded with JSON response ${data}`);
			hide(createAlbumForm);
			location.reload();
		})
		.catch((err) => console.log(err))
}

function readAlbumById(id) {
	fetch(`http://localhost:8082/albums/read/${id}`)
		.then((res) => {
			if (res.ok != true) {
				console.log("Status is not OK!");
			}
			res.json()
				.then((data) => {
					let titleDiv = document.createElement("div");
					let title = document.createElement("h1");
					title.innerHTML = data.name;
					title.setAttribute("class", "album-title")
					titleDiv.style.backgroundImage = `url(${data.cover})`;
					// titleDiv.style.backgroundRepeat="no-repeat";
					titleDiv.style.padding = "20px";
					titleDiv.appendChild(title);

					let artistLink = document.createElement("a");
					artistLink.innerHTML = data.artist.name;
					let genreLink = document.createElement("a");
					genreLink.innerHTML = data.genre.name;
					artistLink.setAttribute("href", `${root}/readArtist.html?id=${data.artist.id}`);
					artistLink.setAttribute("class", "sub-link")
					genreLink.setAttribute("href", `${root}/readGenre.html?id=${data.genre.id}`);
					genreLink.setAttribute("class", "sub-link");
					albumNameDisplay.appendChild(titleDiv);
					albumNameDisplay.appendChild(artistLink);
					albumNameDisplay.appendChild(document.createElement("br"));
					albumNameDisplay.appendChild(genreLink);
					updateAlbumBtn.setAttribute("onclick", `updateAlbum(${data.id})`);
					deleteEachAlbum.setAttribute("onclick", `confirmDeleteAlbum(${data.id})`);
					pageTitle.innerHTML = data.name;
					if (data.tracks.length == 0) {
						tracksInAlbumDiv.innerHTML="This album does not contain any tracks.";
					} else {
						for (let each of data.tracks) {
							createTrackCard(tracksInAlbumDiv, each);
						}
					}
				}).catch((err) => console.log(err))
		})
}


function readAlbumPageLoad() {
	readAlbumById(params.get('id'));
	greetUser();
}

function updateAlbum(id) {
	let formData = {};
	if (albumNameUpdate.value!=""){
		formData.name=albumNameUpdate.value;
	}
	if (albumCoverUpdate.value!=""){
		formData.cover=albumCoverUpdate.value;
	}
	if(albumArtistUpdate.value!=""){
		formData.artist.id=albumArtistUpdate.value;
	}
	if( albumGenreUpdate.value!=""){
		formData.genre.id= albumGenreUpdate.value;
	}
	fetch(`http://localhost:8082/albums/update/${id}`, {
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

function deleteAlbum(id) {
	fetch(`http://localhost:8082/albums/delete/${id}`, {
		method: 'delete',
	})
		.then(res => res.json())
		.then(data => {
			console.log(`Request succeeded with JSON response ${data}`);
			window.location.replace(`${root}/readAlbum.html#deleted`);
		})
		.catch((err) => console.log(err))
}

function confirmDeleteAlbum(id) {
	if (confirm("Are you sure?")) {
		deleteAlbum(id);
		albumNameDisplay.innerHTML = "Album deleted.";
	}
}
//#################################################################################################################
//#################################################################################################################
// METHODS FOR PLAYLISTS 
function createPlaylistCard(display, playlist) {
	let playlistCard = createCardDiv(playlistsPath, playlist);
	let playlistTitle = createCardTitle(playlist.name);
	let playlistArtwork = createCardImg(playlist.artwork);
	let playlistDesc = createCardText(playlist.description);
	playlistCard.appendChild(playlistArtwork);
	playlistCard.appendChild(playlistTitle);
	playlistCard.appendChild(playlistDesc);
	playlistCard.setAttribute("onclick", `goTo(playlistsPath,${playlist.id})`);
	display.appendChild(playlistCard);
}


function createPlaylist() {
	let formData = {
		"name": playlistName.value,
		"artwork": playlistArtwork.value,
		"description": playlistDesc.value,
		"user": {
			"id": userId
		}
	}
	fetch("http://localhost:8082/playlists/create", {
		method: 'post',
		headers: {
			"Content-type": "application/json"
		},
		body: JSON.stringify(formData)
	})
		.then(res => res.json())
		.then(data => {
			console.log(`Request succeeded with JSON response ${data}`);
			hide(createPlaylistForm);
			location.reload();
		})
		.catch((err) => console.log(err))
}

function readPlaylistById(id) {
	fetch(`http://localhost:8082/playlists/read/${id}`)
		.then((res) => {
			if (res.ok != true) {
				console.log("Status is not OK!");
			}
			res.json()
				.then((data) => {
					let titleDiv = document.createElement("div");
					let title = document.createElement("h1");
					title.innerHTML = data.name;
					title.setAttribute("class", "album-title")
					titleDiv.style.backgroundImage = `url(${data.artwork})`;
					titleDiv.style.padding = "20px";
					titleDiv.appendChild(title);
					playlistNameDisplay.appendChild(titleDiv);
					updatePlaylistBtn.setAttribute("onclick", `updatePlaylist(${data.id})`);
					deleteEachPlaylist.setAttribute("onclick", `confirmDeletePlaylist(${data.id})`)
					pageTitle.innerHTML = data.name;
					readTracksInPlaylist(data.id);
				}).catch((err) => console.log(err))
		})
}
function readTracksInPlaylist(id) {
	fetch(`http://localhost:8082/tracks/read/by-playlist/${id}`)
		.then((res) => {
			if (res.ok != true) {
				console.log("Status is not OK!");
			}
			res.json()
				.then((data) => {
					for (let track of data) {
						createTrackCard(tracksInPlaylistDiv, track);
						console.log(track);
					}
				}).catch((err) => console.log(err))
		})
}

function readAlbumPageLoad() {
	readAlbumById(params.get('id'));
	greetUser();
}
function readPlaylistPageLoad() {
	readPlaylistById(params.get('id'));
	greetUser();
}

function updatePlaylist(id) {
	let formData = {};
	if (playlistNameUpdate.value!=""){
		formData.name=playlistNameUpdate.value
	}
	if (playlistArtworkUpdate.value!=""){
		formData.artwork=playlistArtworkUpdate.value
	}
	if (playlistDescUpdate.value!=""){
		formData.description=playlistDescUpdate.value
	}
	if (userId!=""){
		formData.user.id=userId
	}
	fetch(`http://localhost:8082/playlists/update/${id}`, {
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

function deletePlaylist(id) {
	fetch(`http://localhost:8082/playlists/delete/${id}`, {
		method: 'delete',
	})
		.then(res => res.json())
		.then(data => {
			console.log(`Request succeeded with JSON response ${data}`);
			window.location.replace(`${root}/readPlaylist.html#deleted`);
		})
		.catch((err) => console.log(err))
}

function confirmDeletePlaylist(id) {
	if (confirm("Are you sure?")) {
		deletePlaylist(id);
		playlistNameDisplay.innerHTML = "Playlist deleted.";
	}
}
//#################################################################################################################
//#################################################################################################################
// METHODS FOR TRACKS
function createTrackCard(display, track) {
	let trackCard = createCardDiv(tracksPath, track);
	let trackTitle = createCardTitle(track.name);
	let trackDur = createCardSubtitle(`Duration: ${track.duration}s`)
	//let trackAlbum = createCardLink(`${root}/readAlbum.html?id=${track.album.id}`, track.album.name);
	trackCard.appendChild(trackTitle);
	trackCard.appendChild(trackDur);
	//trackCard.appendChild(trackAlbum);
	trackCard.setAttribute("onclick", `goTo(tracksPath,${track.id})`);
	display.appendChild(trackCard);
	console.log(trackCard);
}



function createTrack() {
	let formData = {
		"name": trackName.value,
		"duration": trackDuration.value,
		"lyrics": trackLyrics.value,
		"album": {
			"id": trackAlbum.value
		},
		"playlist": {
			"id": trackPlaylist.value
		}
	}

	// if (trackPlaylist != null) {
	//     formData.playlist.id = trackPlaylist.value;
	// }
	fetch("http://localhost:8082/tracks/create", {
		method: 'post',
		headers: {
			"Content-type": "application/json"
		},
		body: JSON.stringify(formData)
	})
		.then(res => res.json())
		.then(data => {
			console.log(`Request succeeded with JSON response ${data}`);
			hide(createTrackForm);
			location.reload();
		})
		.catch((err) => console.log(err))
}

function readTrackById(id) {
	fetch(`http://localhost:8082/tracks/read/${id}`)
		.then((res) => {
			if (res.ok != true) {
				console.log("Status is not OK!");
			}
			res.json()
				.then((data) => {
					let title = document.createElement("h1");
					title.innerHTML = data.name;
					title.setAttribute("class", "album-title")
					title.style.padding = "20px";
					let dur = document.createElement("h6");
					dur.innerHTML = "Duration: " + data.duration;
					trackNameDisplay.appendChild(title);
					trackNameDisplay.appendChild(dur);
					trackNameDisplay.appendChild(document.createElement("hr"));
					let lyrics = document.createElement("p");
					let text = document.createElement("i");
					text.innerHTML = data.lyrics;
					lyrics.appendChild(text);
					trackLyricsDisplay.appendChild(lyrics);
					updateTrackBtn.setAttribute("onclick", `updateTrack(${data.id})`);
					deleteEachTrack.setAttribute("onclick", `confirmDeleteTrack(${data.id})`)
					pageTitle.innerHTML = data.name;
				}).catch((err) => console.log(err))
		})
}

function readTrackPageLoad() {
	readTrackById(params.get('id'));
	greetUser();
}

function updateTrack(id) {
	let formData = {};
	if (trackNameUpdate.value!=""){
		formData.name=trackNameUpdate.value
	}
	if (trackDurationUpdate.value!=""){
		formData.duration=trackDurationUpdate.value
	}
	if (trackLyricsUpdate.value!=""){
		formData.lyrics=trackLyricsUpdate.value
	}
	if (trackAlbumUpdate.value!=""){
		formData.album.id=trackAlbumUpdate.value
	}
	if (trackPlaylistUpdate.value!=""){
		formData.playlist.id=trackPlaylistUpdate.value
	}
	fetch(`http://localhost:8082/tracks/update/${id}`, {
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

function deleteTrack(id) {
	fetch(`http://localhost:8082/tracks/delete/${id}`, {
		method: 'delete',
	})
		.then(res => res.json())
		.then(data => {
			console.log(`Request succeeded with JSON response ${data}`);
			location.reload();
			window.location.replace(`${root}/readTrack.html#deleted`);
		})
		.catch((err) => console.log(err))
}

function confirmDeleteTrack(id) {
	if (confirm("Are you sure?")) {
		deleteTrack(id);
		eachTrackDiv.innerHTML = "Track deleted.";
	}
}

//#################################################################################################################
//#################################################################################################################
// METHODS FOR USER
function readUserActionsPage() {
	if (params.get('action') == "signUp") {
		userFormToggle(signUpForm);
	} else if (params.get('action') == "login") {
		userFormToggle(signInForm);
	} else {
		confirmUser();
	}
}

function limitCrud() {
	for (let btn of btns) {
		if (status != 1) {
			btn.style.display = "none";
		}
		else {
			btn.style.display = "block";
		}
	}

}
function confirmUser() {
	if (confirm("Do you have an account?\nClick ok if yes.")) {
		window.location.replace(`${root}/user.html?action=login`);
	} else {
		window.location.replace(`${root}/user.html?action=signUp`);
	}
}

function confirmLogout() {
	if (confirm("Are you sure you want to logout?")) {
		logout();
	}
}
function signUp() {
	let formData = {
		"username": usernameUp.value,
		"password": passwordUp.value
	}

	fetch("http://localhost:8082/users/create", {
		method: 'post',
		headers: {
			"Content-type": "application/json",
		},
		body: JSON.stringify(formData)
	})
		.then(res => res.json())
		.then(data => {
			console.log(`User created ${data}`);
			signUpForm.innerHTML = "User created.\n"
			let loginLink = document.createElement("a");
			loginLink.innerHTML = "Sign in";
			loginLink.setAttribute("href", `${root}/user.html?action=login`)
			signUpForm.appendChild(loginLink);
		})
		.catch((err) => console.log(err))
}

function login() {
	fetch("http://localhost:8082/users/login", {
		method: 'post',
		headers: {
			"username": usernameIn.value,
			"password": passwordIn.value
		}
	}).then(res => res.text())
		.then(data => {
			console.log(`User is logged in`);
			window.localStorage.setItem("token", data);
			signInForm.innerHTML = "Successfully Logged in";
			window.localStorage.setItem("currentUser", usernameIn.value);
			window.localStorage.setItem("userStatus", 1);
			greet.innerHTML="Hi, "+usernameIn.value;
			setTimeout(window.location.replace(`${root}/readUser.html`),1000);
		})
		.catch((err) => console.log(err))
}


function greetUser() {
	if (status==1) {
			greet.innerHTML = " Hi, " + currentUser;
			let logoutLink = document.createElement("button");
			logoutLink.setAttribute("class", "btn btn-link");
			logoutLink.setAttribute("onclick", "confirmLogout()");
			logoutLink.innerHTML = "Logout";
			userDisplay.appendChild(logoutLink);
			console.log(window.localStorage);
	}
	limitCrud();
}

 function logout() {
    fetch("http://localhost:8082/users/logout", {
         method: 'post',
         headers: {
            "token": sessionToken
         }
     })
         .then(data => {
            console.log(data);
			body.appendChild(document.createTextNode("Successfully logged out\n"));
            let home=document.createElement("a");
			home.innerHTML="Go to home";
			home.href=`${root}`;
			body.appendChild(home);
			resetCred();
			hide(notNav);
			hide(greet);
			let LOlink=document.querySelector("button[onclick='confirmLogout()']");
			LOlink.remove();
        })
        .catch((err) => console.log(err))
}

function userIconActions() {
		if (window.localStorage.userStatus==1) { //if there is a user logged in
			window.location.replace(`${root}/readUser.html`);
		} else {
			window.location.replace(`${root}/user.html`)
		}
}

function readUserPage() {
	if (status==1){
		readUserByUsername(currentUser);
		greetUser();
	} else {
		body.append(document.createTextNode("You are not logged in."))
		hide(notNav)
	}
}

function readUserByUsername(username) {
	fetch(`http://localhost:8082/users/read-by-username/${username}`)
		.then((res) => {
			if (res.ok != true) {
				console.log("Status is not OK!");
			}
			res.json()
				.then((data) => {
					let title = document.createElement("h1");
					title.innerHTML = data.username;
					userTitle.appendChild(title);
					window.localStorage.setItem("userId",data.id);
					updateUserBtn.setAttribute("onclick","updateUser(userId)");
					deleteUserBtn.setAttribute("onclick","confirmDeleteUser(userId)");
					
					if (data.playlists.length == 0) {
						userPlaylists.innerHTML = "You do not have playlists yet\nGo to ";
						let playlistLink = document.createElement("a");
						playlistLink.setAttribute("href", "playlists.html");
						playlistLink.innerHTML = "playlists";
						userPlaylists.append(playlistLink);
						userPlaylists.append(document.createTextNode(" to create a playlist"));
					} else {
						for (let each of data.playlists) {
							createPlaylistCard(userPlaylists, each);
						}
					}
				}).catch((err) => console.log(err))
		})
}

function updateUser(id) {
	let formData={};
	if (usernameUpdate.value!=""){
		formData.username=usernameUpdate.value;
	}
	if (passwordUpdate.value!=""){
		formData.password=passwordUpdate.value;
	}
	fetch(`http://localhost:8082/users/update/${id}`, {
		method: 'put',
		headers: {
			"token": sessionToken,
			"Content-Type":"application/json"
		},
		body: JSON.stringify(formData),
	}).then(res => res.json())
		.then(data=>{
			hide(notNav);
			body.appendChild(document.createTextNode("User credentials updated."));
			console.log(data.username);
			window.localStorage.setItem("currentUser", data.username);
			greet.innerHTML="Hi, "+data.username;
		})
}

function deleteUser(id) {
	fetch(`http://localhost:8082/users/delete/${id}`, {
		method: 'delete',
		headers: {
			"token": sessionToken
		}
	})
		.then(data => {
			console.log(`Request succeeded with JSON response ${data}`);
			logout();
		})
		.catch((err) => console.log(err))
}

function confirmDeleteUser(id) {
	if (confirm("Are you sure?")) {
		deleteUser(id);
		hide(notNav);
		body.appendChild(document.createTextNode("User deleted.\n"))
	}
}
//#################################################################################################################
//#################################################################################################################
// METHODS FOR ALL ENTITIES

function hideChildren(childGrp){
	for (let child of childGrp){
			hide(child);
		}
}
function leaveAppCheck(){
	if (window.close()){
		resetCred();
	}
}

function resetCred() {
	window.localStorage.clear();
	for (let form of forms){
		form.value="";
	}
}

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

function readIndex() {
	greetUser();
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
								console.log(track);
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
					greetUser();
					console.log(`${path} page loaded.`);
				}).catch((err) => console.log(err))
		})
}

