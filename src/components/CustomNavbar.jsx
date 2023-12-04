import React from 'react';
import { NavLink as ReactLink } from 'react-router-dom';
import {
  Navbar,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  NavbarText,
} from 'reactstrap';
import './CustomNavBar.css'; // Import your custom CSS file

const CustomNavBar = () => {
  return (
    <Navbar className="custom-navbar bg-dark" expand="md">
      <NavbarBrand href="/">MyBlogs</NavbarBrand>
      <Nav className="ml-auto" navbar>
        <NavItem>
          <NavLink tag={ReactLink} to="/home/" className="text-light">
            Home
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink tag={ReactLink} to="/dashboard/" className="text-light">
            Dashboard
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink tag={ReactLink} to="/about/" className="text-light">
            About
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink tag={ReactLink} to="/signup/" className="text-light">
            Sign up
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink tag={ReactLink} to="/login/" className="text-light">
            Sign in
          </NavLink>
        </NavItem>
        <UncontrolledDropdown nav inNavbar>
          <DropdownToggle nav caret className="text-light">
            More
          </DropdownToggle>
          <DropdownMenu right className="custom-dropdown-menu">
            <DropdownItem tag={ReactLink} to="/services/" className="text-dark">
              Services
            </DropdownItem>
            <DropdownItem className="text-dark">Contact Us</DropdownItem>
            <DropdownItem divider />
            <DropdownItem className="text-dark">Youtube</DropdownItem>
          </DropdownMenu>
        </UncontrolledDropdown>
      </Nav>
      <NavbarText className="text-light">Youtube</NavbarText>
    </Navbar>
  );
};

export default CustomNavBar;
