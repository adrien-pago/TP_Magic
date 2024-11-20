package edu.esia.tpmagicadrien.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import edu.esia.tpmagicadrien.R;
import edu.esia.tpmagicadrien.model.AbstractCard;
import edu.esia.tpmagicadrien.model.AbstractCardWithCost;
import edu.esia.tpmagicadrien.model.Cost;
import edu.esia.tpmagicadrien.model.Creature;
import edu.esia.tpmagicadrien.model.enums.CardType;
import edu.esia.tpmagicadrien.model.enums.Color;
import edu.esia.tpmagicadrien.view.utils.CardTypeSpinnerAdapter;
import edu.esia.tpmagicadrien.viewmodel.MagicViewModel;

// Fragment pour éditer une carte
public class CardEditFragment extends Fragment {

    // Taille de l'icône de coût
    private static final int COST_ICON_SIZE = 65;
    // ViewModel pour accéder aux données
    private MagicViewModel model;

    // Carte actuellement sélectionnée
    private AbstractCard curCard;
    // Indique si c'est une nouvelle carte
    private boolean isNewCard = false;

    // Layout principal
    private ConstraintLayout layout;
    // TextView pour le nom de la carte
    private TextView tvName;

    // TextView pour le premier coût
    private TextView tvFirstCost;

    // Liste de tous les TextView de coût
    private final List<TextView> allCostTextViews = new ArrayList<>();

    // Layout pour les coûts
    private LinearLayout layoutCosts;

    // Spinner pour le type de carte
    private Spinner spinCardType;
    // EditText pour l'attaque
    EditText etAtk;
    // TextView pour le slash
    TextView tvSlash;
    // EditText pour la défense
    EditText etDef;

    // Constructeur public requis
    public CardEditFragment() {
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = new ViewModelProvider(getActivity()).get(MagicViewModel.class);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        this.model = new ViewModelProvider(getActivity()).get(MagicViewModel.class);
        this.curCard = this.model.getCurCard();
        this.isNewCard = this.curCard == null;
        final View v = inflater.inflate(R.layout.fragment_card_edit, container, false);
        this.layout = v.findViewById(R.id.layout_card_edit);
        this.tvName = v.findViewById(R.id.et_cardedit_name);
        this.tvFirstCost = v.findViewById(R.id.tv_first_cost);
        this.allCostTextViews.add(0, this.tvFirstCost);
        this.layoutCosts = v.findViewById(R.id.layout_card_cost);
        this.spinCardType = v.findViewById(R.id.spin_card_type);
        this.etAtk = v.findViewById(R.id.et_card_atk);
        this.tvSlash = v.findViewById(R.id.tv_card_slash);
        this.etDef = v.findViewById(R.id.et_card_def);
        setBackground();
        setCostIcons();
        setCardType();
        if (this.isNewCard) {
            this.tvName.setFocusedByDefault(true);
        } else {
            this.tvName.setText(this.curCard.getName());
            if (CardType.CREATURE.equals(this.curCard.getType())) {
                setCreatureAtkDef();
            } else {
                hideCreatureAtkDef();
            }
        }
        return v;
    }

    // Définir le type de carte
    private void setCardType() {
        final CardTypeSpinnerAdapter adapter = new CardTypeSpinnerAdapter();
        this.spinCardType.setAdapter(adapter);
        if (!this.isNewCard) {
            this.spinCardType.setSelection(this.curCard.getType().ordinal());
        }
    }

    // Définir les icônes de coût
    private void setCostIcons() {
        if (this.isNewCard) {
            this.tvFirstCost.setBackgroundResource(R.drawable.mana_incolore_inactif);
        } else {
            if (this.curCard instanceof AbstractCardWithCost) {
                boolean first = true;
                for (final Cost curCost : ((AbstractCardWithCost) this.curCard).getCosts()) {
                    setCost(curCost, first);
                    first = false;
                }
            } else {
                this.tvFirstCost.setBackgroundResource(R.drawable.mana_incolore_inactif);
                this.tvFirstCost.setText("");
            }
        }
    }

    // Définir un coût
    private void setCost(final Cost cost, final boolean first) {
        switch (cost.getColor()) {
            case COLORLESS:
                if (first) {
                    this.tvFirstCost.setBackgroundResource(R.drawable.mana_incolore_inactif);
                    this.tvFirstCost.setText(Integer.toString(cost.getAmount()));
                } else {
                    createColorlessIcon(cost.getAmount());
                }
                break;
            default:
                if (first) {
                    this.tvFirstCost.setText("");
                    this.tvFirstCost.setBackgroundResource(cost.getColor().getInactiveDrawableId());
                } else {
                    createCostIcon(cost.getColor());
                }
                for (int i = 1; i < cost.getAmount(); i++) {
                    createCostIcon(cost.getColor());
                }
        }
    }

    // Créer une icône de coût
    private void createCostIcon(final Color color) {
        final ImageView tvCost = new ImageView(this.getContext());
        tvCost.setBackgroundResource(color.getInactiveDrawableId());
        tvCost.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.layoutCosts.addView(tvCost);
        tvCost.setLayoutParams(new LinearLayout.LayoutParams(COST_ICON_SIZE, COST_ICON_SIZE));
    }

    // Créer une icône de coût incolore
    private void createColorlessIcon(final int amount) {
        final TextView tvCost = new TextView(this.getContext());
        tvCost.setText(Integer.toString(amount));
        tvCost.setBackgroundResource(R.drawable.mana_incolore_inactif);
        tvCost.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        this.layoutCosts.addView(tvCost);
    }

    // Définir l'arrière-plan
    private void setBackground() {
        if (this.isNewCard) {
            this.layout.setBackgroundResource(Color.COLORLESS.getBgCardId());
        } else {
            if (CardType.CREATURE.equals(this.curCard.getType())) {
                this.layout.setBackgroundResource(this.curCard.getColor().getBgCreatureId());
            } else {
                this.layout.setBackgroundResource(this.curCard.getColor().getBgCardId());
            }
        }
    }

    // Définir l'attaque et la défense de la créature
    private void setCreatureAtkDef() {
        final Creature tmp = (Creature) this.curCard;
        this.etAtk.setVisibility(View.VISIBLE);
        this.tvSlash.setVisibility(View.VISIBLE);
        this.etDef.setVisibility(View.VISIBLE);
        this.etAtk.setText(Integer.toString(tmp.getAttack()), TextView.BufferType.EDITABLE);
        this.etDef.setText(Integer.toString(tmp.getDefense()), TextView.BufferType.EDITABLE);
    }

    // Cacher l'attaque et la défense de la créature
    private void hideCreatureAtkDef() {
        this.etAtk.setVisibility(View.INVISIBLE);
        this.tvSlash.setVisibility(View.INVISIBLE);
        this.etDef.setVisibility(View.INVISIBLE);
    }

}