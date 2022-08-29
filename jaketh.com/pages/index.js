import Layout from '../components/Layout';
import NameSlide from '../components/NameSlide';
import Abilities from '../components/Abilities';
import OtherInfo from '../components/OtherInfo';

export default function Index() {
  return (
    <Layout>
        <NameSlide/>
        <Abilities/>
        <OtherInfo/>
    </Layout>
  );
}