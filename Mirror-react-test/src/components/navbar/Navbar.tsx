import React from 'react'
import './index.css'

export function Navbar() {
    return (
        <div className='navbar'>
            <a className='logo'>MIRROR</a>
            <nav>
                <ul className='menu'>
                    <li><a href="">Início</a></li>
                    <li><a href="">Sobre</a></li>
                    <li><a href="">Inscreva-se</a></li>
                    <li><a href="">Login</a></li>
                </ul>
            </nav>
        </div>
    )
}


export default Navbar

