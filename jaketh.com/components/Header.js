import Link from 'next/link';

const Header = () => (

    <div className = 'container'>
        <div>
            <Link href="/">
                <a>Jaketh.com</a>
            </Link>
            <Link href="/projects">
                <a>Projects</a>
            </Link>
            <Link href="/blog">
                <a>Blog</a>
            </Link>
            <Link href="/about">
                <a>About</a>
            </Link>
        </div>
        <div>
            <Link href="/contact">
                <a>Contact</a>
            </Link>
        </div>
        <style jsx>{`       

                .container {
                    padding-top: 30px;
                    padding-bottom: 30px;
                    padding-left: 10px;
                    padding-right: 10px;

                    z-index: 1000;

                    background: rgb(71, 84, 232);
                    background: -webkit-linear-gradient(right, rgb(71, 84, 232), rgb(183, 71, 232),);
                    background: -o-linear-gradient(left, rgb(183, 71, 232), rgb(71, 84, 232));
                    background: -moz-linear-gradient(left, rgb(183, 71, 232), rgb(71, 84, 232));
                    background: linear-gradient(to left, rgb(183, 71, 232), rgb(71, 84, 232));
                }

                div {
                    padding-left: 12px;
                    padding-right: 12px;

                    display: flex;
                    justify-content: space-between;
                }

                li {
                    list-style: none;
                    margin: 5px 0;
                }

                a {
                    margin-left: 10px;
                    margin-right: 10px;
                    font-size: 22px;
                    text-decoration: none;
                    color: white;
                    font-family: 'Roboto', Arial, sans-serif;
                }

                a:hover {
                    opacity: 0.6;
                }
            `}</style>
    
    </div>

);

export default Header;