import React from 'react';
import Layout from '../components/Layout';

export default function About() {
    return(
        <Layout>
            <div id="about-container">


                <h1 id="title">About</h1>
            </div>
            
            <style jsx>{`
            #about-container {
                position: relative;
                height: 100%;
            }

            #title {
                margin-top: 10vh;
                color: white;
                font-size: 2em;
                font-family: 'Roboto', open-sans;
                text-align: center;
            }
            `}</style>
        </Layout>   
    )
}