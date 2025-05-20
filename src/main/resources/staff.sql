-- Create staff table
CREATE TABLE IF NOT EXISTS staff (
    staffId INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    position TEXT NOT NULL,
    address TEXT NOT NULL,
    status TEXT NOT NULL DEFAULT 'ACTIVE'
);

-- Insert sample staff data
INSERT INTO staff (name, email, position, address, status) VALUES
('John Smith', 'john.smith@iotbay.com', 'Manager', '123 George Street, Sydney NSW 2000', 'ACTIVE'),
('Sarah Johnson', 'sarah.j@iotbay.com', 'Salesperson', '45 Pitt Street, Sydney NSW 2000', 'ACTIVE'),
('Michael Brown', 'michael.b@iotbay.com', 'IT Support', '78 Market Street, Sydney NSW 2000', 'ACTIVE'),
('Emma Wilson', 'emma.w@iotbay.com', 'Salesperson', '90 Castlereagh Street, Sydney NSW 2000', 'ACTIVE'),
('David Lee', 'david.l@iotbay.com', 'Manager', '34 Macquarie Street, Sydney NSW 2000', 'ACTIVE'),
('Lisa Chen', 'lisa.c@iotbay.com', 'IT Support', '56 York Street, Sydney NSW 2000', 'ACTIVE'),
('James Taylor', 'james.t@iotbay.com', 'Salesperson', '67 Clarence Street, Sydney NSW 2000', 'ACTIVE'),
('Sophie Anderson', 'sophie.a@iotbay.com', 'Manager', '89 Kent Street, Sydney NSW 2000', 'ACTIVE'),
('Robert White', 'robert.w@iotbay.com', 'IT Support', '12 Sussex Street, Sydney NSW 2000', 'ACTIVE'),
('Olivia Martin', 'olivia.m@iotbay.com', 'Salesperson', '23 Bridge Street, Sydney NSW 2000', 'ACTIVE'),
('William Thompson', 'william.t@iotbay.com', 'Manager', '45 Martin Place, Sydney NSW 2000', 'ACTIVE'),
('Isabella Garcia', 'isabella.g@iotbay.com', 'IT Support', '67 Elizabeth Street, Sydney NSW 2000', 'ACTIVE'),
('Daniel Martinez', 'daniel.m@iotbay.com', 'Salesperson', '89 Park Street, Sydney NSW 2000', 'ACTIVE'),
('Charlotte Robinson', 'charlotte.r@iotbay.com', 'Manager', '12 King Street, Sydney NSW 2000', 'ACTIVE'),
('Joseph Clark', 'joseph.c@iotbay.com', 'IT Support', '34 Hunter Street, Sydney NSW 2000', 'ACTIVE'),
('Amelia Rodriguez', 'amelia.r@iotbay.com', 'Salesperson', '56 Phillip Street, Sydney NSW 2000', 'ACTIVE'),
('Benjamin Lewis', 'benjamin.l@iotbay.com', 'Manager', '78 Bligh Street, Sydney NSW 2000', 'ACTIVE'),
('Mia Walker', 'mia.w@iotbay.com', 'IT Support', '90 Margaret Street, Sydney NSW 2000', 'ACTIVE'),
('Lucas Hall', 'lucas.h@iotbay.com', 'Salesperson', '23 Macquarie Place, Sydney NSW 2000', 'ACTIVE'),
('Harper Young', 'harper.y@iotbay.com', 'Manager', '45 Bridge Street, Sydney NSW 2000', 'ACTIVE'); 