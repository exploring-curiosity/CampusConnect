CREATE TABLE event_attendees (
    event_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);