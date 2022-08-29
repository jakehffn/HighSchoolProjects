import Header from './Header';
import Head from 'next/head';
import Footer from './Footer';

const layoutStyle = {
    margin: 0,
    padding: 0
    

};

const Layout = props => (
    <div>
        <Head>
            <title>Jacob Hoffman</title>
            <style>{'body { background-color: rgb(39, 42, 43); margin: 0}'}</style>

            <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet"></link>
            <link href="https://fonts.googleapis.com/css?family=Montserrat|Roboto&display=swap" rel="stylesheet"></link>
            <link href="https://fonts.googleapis.com/css?family=Montserrat|Roboto|Roboto+Mono&display=swap" rel="stylesheet"></link>
        </Head>

        <div style={layoutStyle}>
            <Header />
            {props.children}
            <Footer />
        </div>
        <style jsx>{`
        * {
            min-width: 700px;
        }
        `}</style>
    </div>
);

export default Layout;