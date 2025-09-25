CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    department VARCHAR(50),
    role VARCHAR(50),
    batch VARCHAR(10) NOT NULL
);

CREATE TABLE events (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    description TEXT,
    organizer_id UUID NOT NULL,
    date TIMESTAMP NOT NULL,
    location VARCHAR(255) NOT NULL,
    is_public BOOLEAN NOT NULL,
    access_code VARCHAR(255),
    open_to VARCHAR(50)[],
    max_attendees INT,
    is_closed BOOLEAN DEFAULT FALSE,
    closing_note TEXT
);