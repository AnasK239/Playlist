-- Seed Users
INSERT INTO users (id, name)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'John Doe'),
    ('22222222-1111-1111-1111-111111111111', 'Jane Smith'),
    ('33333333-1111-1111-1111-111111111111', 'Alice Johnson'),
    ('44444444-1111-1111-1111-111111111111', 'Bob Williams'),
    ('55555555-1111-1111-1111-111111111111', 'Charlie Davis')
    ON CONFLICT (id) DO NOTHING;

-- Seed Songs
INSERT INTO songs (id, name, title, artist, album, genre)
VALUES
    ('11111111-2222-2222-2222-222222222222', 'Bohemian Rhapsody', 'Bohemian Rhapsody', 'Queen', 'A Night at the Opera', 'Rock'),
    ('22222222-2222-2222-2222-222222222222', 'Blinding Lights', 'Blinding Lights', 'The Weeknd', 'After Hours', 'Synthwave'),
    ('33333333-2222-2222-2222-222222222222', 'Shape of You', 'Shape of You', 'Ed Sheeran', 'Divide', 'Pop'),
    ('44444444-2222-2222-2222-222222222222', 'Take Five', 'Take Five', 'The Dave Brubeck Quartet', 'Time Out', 'Jazz'),
    ('55555555-2222-2222-2222-222222222222', 'Enter Sandman', 'Enter Sandman', 'Metallica', 'Metallica', 'Metal'),
    ('66666666-2222-2222-2222-222222222222', 'Midnight City', 'Midnight City', 'M83', 'Hurry Up We Are Dreaming', 'Electronic')
    ON CONFLICT (id) DO NOTHING;