<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Film upload</title>
</head>
<body>
    <form name="film_upload_form" method="post" action="/controller" enctype="multipart/form-data">
        <input type="hidden" name="command" value="film_upload"/>

        <label for="title">Title</label>
        <input type="text" id="title" placeholder="" name="title" required><br>

        <label for="genre">Genre</label>
        <input type="text" id="genre" placeholder="" name="genre" required><br>

        <label for="director">Director</label>
        <input type="text" id="director" placeholder="" name="director" required><br>

        <label for="writer">Writer</label>
        <input type="text" id="writer" placeholder="" name="writer" required><br>

        <label for="actors">Actors</label>
        <input type="text" id="actors" placeholder="" name="actors" required><br>

        <label for="country">Country</label>
        <input type="text" id="country" placeholder="" name="country" required><br>

        <label for="release_date">Release date</label>
        <input type="date" id="release_date" placeholder="" name="release_date" required><br>

        <p>Duration</p>
        <label for="hours">Hours</label>
        <input type="number" id="hours" placeholder="" name="hours" required>
        <label for="minutes">Minutes</label>
        <input type="number" id="minutes" placeholder="" name="minutes" required><br>

        <label for="age_limit">Age limit</label>
        <input type="number" id="age_limit" placeholder="" name="age_limit" required><br>

        <label for="description">Description</label>
        <textarea id="description" name="description" required>

        </textarea><br>

        <label for="poster">Poster</label>
        <input type="file" id="poster" placeholder="" name="poster" accept="image/jpeg,image/png"><br>

        <button type="submit">Submit</button>
    </form>
</body>
</html>
