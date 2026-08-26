INSERT INTO users (id, name)
VALUES ('11111111-1111-1111-1111-111111111111', 'John Doe')
    ON CONFLICT (id) DO NOTHING;

INSERT INTO songs (id, name, title, artist, album, genre)
VALUES ('22222222-2222-2222-2222-222222222222', 'Song A', 'Song A', 'Artist A', 'Album A', 'Rock')
    ON CONFLICT (id) DO NOTHING;